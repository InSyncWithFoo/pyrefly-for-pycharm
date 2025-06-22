package insyncwithfoo.pyrefly.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import insyncwithfoo.pyrefly.isPythonFile
import insyncwithfoo.pyrefly.message
import insyncwithfoo.pyrefly.path
import org.eclipse.lsp4j.ClientCapabilities
import java.nio.file.Path


internal class PyreflyServerDescriptor(project: Project, private val executable: Path) :
    ProjectWideLspServerDescriptor(project, PRESENTABLE_NAME) {
    
    override val clientCapabilities: ClientCapabilities
        get() = super.clientCapabilities.apply {
            textDocument.apply {
                diagnostic = null
            }
        }
    
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
