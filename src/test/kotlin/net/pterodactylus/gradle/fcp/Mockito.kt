package net.pterodactylus.gradle.fcp

import org.mockito.Mockito
import org.mockito.stubbing.Answer

inline fun <reified T> mock(defaultAnswer: Answer<Any> = Mockito.RETURNS_DEFAULTS) = Mockito.mock(T::class.java, defaultAnswer)!!
