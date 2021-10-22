package com.simple.gradle.tasks

import com.simple.gradle.extensions.GradleNotifierConfig
import com.simple.gradle.utils.Constants.TASK_CONFIG_EXTENSION_NAME
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class FailureReporterTask : DefaultTask() {

    @TaskAction
    fun run() {
        val extension = project.extensions.findByName(TASK_CONFIG_EXTENSION_NAME) as GradleNotifierConfig
        extension.failureCommandArguments?.let { commandArgs ->
            println("Reporting failure for task  ${extension.taskForWatchingSuccess}")
            Runtime.getRuntime().exec(commandArgs.toTypedArray())
        } ?: run {
            println("No failure config to report, please set '$TASK_CONFIG_EXTENSION_NAME' extension")
        }
    }
}