package insyncwithfoo.pyrefly

import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.project.guessProjectDir
import com.intellij.openapi.vfs.toNioPathOrNull
import java.nio.file.Path


private val projectManager: ProjectManager
    get() = ProjectManager.getInstance()


internal val openProjects: Sequence<Project>
    get() = projectManager.openProjects.asSequence()


internal val Project.path: Path?
    get() = guessProjectDir()?.toNioPathOrNull()?.toNullIfNotExists()
        ?: basePath?.toPathOrNull()?.toNullIfNotExists()


/**
 * Whether a project:
 * * Is not the pseudo project used to store
 *   default settings for newly created projects, and
 * * Has not been disposed of.
 */
internal val Project.isNormal: Boolean
    get() = !this.isDefault && !this.isDisposed
