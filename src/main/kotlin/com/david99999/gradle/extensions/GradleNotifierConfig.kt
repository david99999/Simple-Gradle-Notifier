package com.david99999.gradle.extensions

/**
 * Configuration class containing the names of the tasks to be monitored,
 * and the commands to be provided for the shell executions on each case
 */
open class GradleNotifierConfig {
    /**
     * pure gradle task name, e.g: "assembleDebug" or "assembleSugarFlavorDebug"
     */
    var taskForWatchingSuccess: String? = null

    /**
     * pure gradle task name, e.g: "assembleDebug" or "assembleSugarFlavorDebug"
     */
    var taskForWatchingFailure: String? = null

    /**
     * Command with arguments to be executed on shell
     * e.g: "say Hi my friend, your task has finished successfully".split(" ")
     */
    var successCommandArguments: List<String>? = null

    /**
     * Command with arguments to be executed on shell
     * e.g: "say Hi my friend, your task has failed".split(" ")
     */
    var failureCommandArguments: List<String>? = null
}