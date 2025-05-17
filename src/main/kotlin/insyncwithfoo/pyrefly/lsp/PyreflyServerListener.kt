package insyncwithfoo.pyrefly.lsp

import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.LspServerListener
import insyncwithfoo.pyrefly.createSettingsObject
import insyncwithfoo.pyrefly.getServers
import insyncwithfoo.pyrefly.lspServerManager
import org.eclipse.lsp4j.DidChangeConfigurationParams
import org.eclipse.lsp4j.InitializeResult


internal class PyreflyServerListener(private val project: Project) : LspServerListener {
    
    override fun serverInitialized(params: InitializeResult) {
        // Technically Pyrefly doesn't read this.
        // It just needs the `settings` property to be set.
        val settings = project.createSettingsObject()
        val parameters = DidChangeConfigurationParams(settings)
        
        project.lspServerManager.getServers<PyreflyServerSupportProvider>().forEach { lspServer ->
            lspServer.sendNotification {
                it.workspaceService.didChangeConfiguration(parameters)
            }
        }
    }
    
}
