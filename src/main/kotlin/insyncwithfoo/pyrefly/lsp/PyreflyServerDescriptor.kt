package insyncwithfoo.pyrefly.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import insyncwithfoo.pyrefly.isPythonFile
import insyncwithfoo.pyrefly.message
import insyncwithfoo.pyrefly.path
import java.nio.file.Path


@Suppress("UnstableApiUsage")
internal class PyreflyServerDescriptor(project: Project, private val executable: Path) :
    ProjectWideLspServerDescriptor(project, PRESENTABLE_NAME) {
    
    override fun isSupportedFile(file: VirtualFile) =
        file.isPythonFile
    
    override fun createCommandLine() = GeneralCommandLine().apply {
        withWorkingDirectory(project.path)
        withCharset(Charsets.UTF_8)
        
        withExePath(executable.toString())
        addParameter("lsp")
    }
    
    companion object {
        private val PRESENTABLE_NAME = message("languageServer.presentableName")
    }
    
}
