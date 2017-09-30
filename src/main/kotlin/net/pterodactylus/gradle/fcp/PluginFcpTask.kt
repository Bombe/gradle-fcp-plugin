package net.pterodactylus.gradle.fcp

import org.gradle.api.DefaultTask

/**
 * Task that communications with a plugin on a Freenet node using FCP.
 */
open class PluginFcpTask : DefaultTask() {

	val host get() = project.extensions.get<FcpPluginExtension>("fcp").host
	val port get() = project.extensions.get<FcpPluginExtension>("fcp").port

}
