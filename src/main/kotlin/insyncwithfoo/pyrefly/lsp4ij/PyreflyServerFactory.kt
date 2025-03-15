package insyncwithfoo.pyrefly.lsp4ij

import com.intellij.openapi.project.Project
import com.redhat.devtools.lsp4ij.LanguageServerEnablementSupport
import com.redhat.devtools.lsp4ij.LanguageServerFactory
import com.redhat.devtools.lsp4ij.client.LanguageClientImpl
import com.redhat.devtools.lsp4ij.server.StreamConnectionProvider
import insyncwithfoo.pyrefly.configurations.PyreflyConfigurationService
import insyncwithfoo.pyrefly.configurations.RunningMode
import insyncwithfoo.pyrefly.configurations.pyreflyConfigurations
import insyncwithfoo.pyrefly.configurations.pyreflyExecutable


internal const val SERVER_ID = "insyncwithfoo.pyrefly"


internal class PyreflyServerFactory : LanguageServerFactory, LanguageServerEnablementSupport {
    
    override fun isEnabled(project: Project): Boolean {
        val configurations = pyreflyConfigurations
        val executable = pyreflyExecutable
        
        return configurations.runningMode == RunningMode.LSP4IJ && executable != null
    }
    
    override fun setEnabled(enabled: Boolean, project: Project) {
        PyreflyConfigurationService.getInstance().state.apply {
            runningMode = when {
                enabled -> RunningMode.LSP4IJ
                else -> RunningMode.DISABLED
            }
        }
    }
    
    override fun createConnectionProvider(project: Project): StreamConnectionProvider {
        return PyreflyServerConnectionProvider.create(project)
    }
    
    override fun createLanguageClient(project: Project): LanguageClientImpl {
        return PyreflyServerClient(project)
    }
    
}
