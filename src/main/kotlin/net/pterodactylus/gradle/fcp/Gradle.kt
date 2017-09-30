package net.pterodactylus.gradle.fcp

import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.tasks.TaskContainer

@Suppress("UNCHECKED_CAST")
fun <T> ExtensionContainer.get(name: String) = getByName(name) as T

@Suppress("UNCHECKED_CAST")
fun <T> TaskContainer.get(name: String) = getByName(name) as T
