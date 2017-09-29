package net.pterodactylus.gradle.fcp

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Gradle plugin for communicating with a Freenet node via FCP.
 */
class FcpPlugin : Plugin<Project> {

	override fun apply(project: Project) {
		project.extensions.create("fcp", FcpPluginExtension::class.java)
		project.tasks.create("pluginFcp", PluginFcpTask::class.java)
	}

}
