package insyncwithfoo.pyrefly.common

import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.projectRoots.ProjectJdkTable
import com.intellij.openapi.projectRoots.Sdk
import com.redhat.devtools.lsp4ij.LanguageServiceAccessor
import insyncwithfoo.pyrefly.configurations.RunningMode
import insyncwithfoo.pyrefly.configurations.pyreflyConfigurations
import insyncwithfoo.pyrefly.createSettingsObject
import insyncwithfoo.pyrefly.getServers
import insyncwithfoo.pyrefly.languageServerManager
import insyncwithfoo.pyrefly.lsp.PyreflyServerSupportProvider
import insyncwithfoo.pyrefly.lsp4ij.SERVER_ID
import insyncwithfoo.pyrefly.lspServerManager
import insyncwithfoo.pyrefly.openProjects
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.eclipse.lsp4j.DidChangeConfigurationParams


@Serializable
private data class SDKSurrogate(
    val name: String,
    val versionString: String?,
    val homePath: String?,
    val homeDirectory: String?
) {
    override fun toString() = Json.encodeToString(this)
}


private fun SDKSurrogate(sdk: Sdk) = with(sdk) {
    SDKSurrogate(name, versionString, homePath, homeDirectory?.toString())
}


internal class ProjectInterpreterChangeListener : ProjectJdkTable.Listener {
    
    override fun jdkAdded(jdk: Sdk) {
        thisLogger().warn(SDKSurrogate(jdk).toString())
        sendDidChangeConfigurationToAllServers()
    }
    
    override fun jdkRemoved(jdk: Sdk) {
        thisLogger().warn(SDKSurrogate(jdk).toString())
        sendDidChangeConfigurationToAllServers()
    }
    
    private fun sendDidChangeConfigurationToAllServers() = openProjects.forEach { project ->
        // Technically Pyrefly doesn't read this.
        // It just needs the `settings` property to be set.
        val settings = project.createSettingsObject()
        val parameters = DidChangeConfigurationParams(settings)
        
        val runningMode = pyreflyConfigurations.runningMode
        
        when (runningMode) {
            RunningMode.DISABLED -> {}
            RunningMode.LSP4IJ -> project.sendToLSP4IJServers(parameters)
            RunningMode.LSP -> project.sendToNativeClientServers(parameters)
        }
    }
    
    private fun Project.sendToNativeClientServers(parameters: DidChangeConfigurationParams) {
        lspServerManager.getServers<PyreflyServerSupportProvider>().map { server ->
            server.sendNotification {
                it.workspaceService.didChangeConfiguration(parameters)
            }
        }
    }
    
    @Suppress("UnstableApiUsage")
    private fun Project.sendToLSP4IJServers(parameters: DidChangeConfigurationParams) {
        try {
            // LanguageServiceAccessor is internal
            val accessor = LanguageServiceAccessor.getInstance(this)
            val allServers = accessor.getLanguageServers(null, null).get() ?: return
            
            for (serverItem in allServers) {
                if (serverItem.serverDefinition.id == SERVER_ID) {
                    serverItem?.server?.workspaceService?.didChangeConfiguration(parameters)
                }
            }
        } catch (_: Throwable) {
            val serverItem = languageServerManager.getLanguageServer(SERVER_ID).get()
            
            serverItem?.server?.workspaceService?.didChangeConfiguration(parameters)
        }
    }
    
}
