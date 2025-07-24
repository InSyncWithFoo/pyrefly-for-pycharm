package insyncwithfoo.pyrefly

import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.project.guessProjectDir
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.vfs.toNioPathOrNull
import com.jetbrains.python.sdk.PythonSdkUtil
import java.nio.file.Path


private val projectManager: ProjectManager
    get() = ProjectManager.getInstance()


internal val openProjects: Sequence<Project>
    get() = projectManager.openProjects.asSequence().filter { it.isNormal }


internal val Project.path: Path?
    get() = guessProjectDir()?.toNioPathOrNull()?.toNullIfNotExists()
        ?: basePath?.toPathOrNull()?.toNullIfNotExists()


private val Project.rootManager: ProjectRootManager
    get() = ProjectRootManager.getInstance(this)


private val Project.sdk: Sdk?
    get() = rootManager.projectSdk?.takeIf { PythonSdkUtil.isPythonSdk(it) }


internal val Project.interpreterPath: Path?
    get() = sdk?.homePath?.toPathIfItExists()


/**
 * Whether a project:
 * * Is not the pseudo project used to store
 *   default settings for newly created projects, and
 * * Has not been disposed of.
 */
internal val Project.isNormal: Boolean
    get() = !this.isDefault && !this.isDisposed
