package io.github.david99999.gradle

import io.github.david99999.gradle.extensions.GradleNotifierConfig
import io.github.david99999.gradle.utils.Constants.REPORT_FAILURE_TASK_NAME
import io.github.david99999.gradle.utils.Constants.REPORT_SUCCESS_TASK_NAME
import io.github.david99999.gradle.utils.Constants.REPORT_TASK_STATUS_GROUP_NAME
import io.github.david99999.gradle.utils.Constants.TASK_CONFIG_EXTENSION_NAME
import io.github.david99999.gradle.tasks.FailureReporterTask
import io.github.david99999.gradle.tasks.SuccessReporterTask
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Plugin for notifying (via shell commands) success and failures for specific gradle tasks,
 * requires a [GradleNotifierConfig] config for setting up the proper task tracking and notifying
 */
class NotifierPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val config = project.extensions.create(TASK_CONFIG_EXTENSION_NAME, GradleNotifierConfig::class.java)
        val successReportTask = project.tasks.create(REPORT_SUCCESS_TASK_NAME, SuccessReporterTask::class.java)
        val failureReportTask = project.tasks.create(REPORT_FAILURE_TASK_NAME, FailureReporterTask::class.java)

        successReportTask.group = REPORT_TASK_STATUS_GROUP_NAME
        failureReportTask.group = REPORT_TASK_STATUS_GROUP_NAME

        project.tasks.whenTaskAdded { addedTask ->
            val taskForWatchingSuccess = config.taskForWatchingSuccess
            val taskForWatchingFailure = config.taskForWatchingFailure
            /**
             * Add the successful task watcher only if success configuration provided
             */
            if (taskForWatchingSuccess != null && addedTask.name == taskForWatchingSuccess) {
                successReportTask.onlyIf { project.tasks.getByName(taskForWatchingSuccess).state.failure == null }
                addedTask.finalizedBy(successReportTask)
            }
            /**
             * Add the failure task watcher only if fail configuration provided
             */
            else if (taskForWatchingFailure != null && addedTask.name == taskForWatchingFailure) {
                failureReportTask.onlyIf {
                    project.tasks.getByName(taskForWatchingFailure).state.failure != null
                }
                addedTask.finalizedBy(failureReportTask)
            }
        }
    }
}