package insyncwithfoo.pyrefly.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerListener
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import insyncwithfoo.pyrefly.createSettingsObject
import insyncwithfoo.pyrefly.isPythonFile
import insyncwithfoo.pyrefly.message
import insyncwithfoo.pyrefly.path
import org.eclipse.lsp4j.ClientCapabilities
import org.eclipse.lsp4j.ConfigurationItem
import java.nio.file.Path


internal class PyreflyServerDescriptor(project: Project, private val executable: Path) :
    ProjectWideLspServerDescriptor(project, PRESENTABLE_NAME) {
    
    override val lspServerListener: LspServerListener
        get() = PyreflyServerListener(project)
    
    override val clientCapabilities: ClientCapabilities
        get() = super.clientCapabilities.apply {
            textDocument.apply {
                diagnostic = null
            }
            workspace.apply {
                configuration = true
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
    
    override fun getWorkspaceConfiguration(item: ConfigurationItem): Any? {
        val settings = project.createSettingsObject()
        
        return when (item.section) {
            "python" -> settings.python
            else -> null
        }
    }
    
    companion object {
        private val PRESENTABLE_NAME = message("languageServer.presentableName")
    }
    
}
