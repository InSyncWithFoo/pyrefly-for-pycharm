package insyncwithfoo.pyrefly

import com.intellij.execution.configurations.PathEnvironmentVariableUtil
import java.io.File
import java.nio.file.Files
import java.nio.file.InvalidPathException
import java.nio.file.Path
import kotlin.io.path.exists


private val Path.isEmpty: Boolean
    get() = this.toString() == ""


/**
 * Parse the string and return a [Path]
 * if the new [Path] is not blank or invalid.
 */
internal fun String.toPathOrNull() =
    try {
        Path.of(this).takeUnless { it.isEmpty }
    } catch (_: InvalidPathException) {
        null
    }


/**
 * Attempt to convert the string to a normalized [Path],
 * then call [toNullIfNotExists] on it.
 */
internal fun String.toPathIfItExists() =
    this.toPathOrNull()?.normalize()?.toNullIfNotExists()


/**
 * Return the path unchanged if it is occupied.
 * Otherwise, return `null`.
 *
 * This necessarily uses [File.exists] rather than [Path.exists],
 * which itself calls [Files.exists].
 */
internal fun Path.toNullIfNotExists() =
    this.takeIf { it.toFile().exists() }


/**
 * Look for [name] in PATH.
 */
internal fun findExecutableInPath(name: String) =
    PathEnvironmentVariableUtil.findExecutableInPathOnAnyOS(name)?.toPath()
