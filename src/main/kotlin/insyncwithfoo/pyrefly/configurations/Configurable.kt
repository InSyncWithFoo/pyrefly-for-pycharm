package insyncwithfoo.pyrefly.configurations

import com.intellij.openapi.options.Configurable
import com.intellij.util.xmlb.XmlSerializerUtil
import insyncwithfoo.pyrefly.lsp.PyreflyServerSupportProvider
import insyncwithfoo.pyrefly.lsp4ij.SERVER_ID
import insyncwithfoo.pyrefly.openProjects
import insyncwithfoo.pyrefly.restartNativeServers
import insyncwithfoo.pyrefly.toggleLSP4IJServers


internal class PyreflyConfigurable : Configurable {
    
    private val service = PyreflyConfigurationService.getInstance()
    private val state = service.state.copy()
    private val panel by lazy { makePanel(state) }
    
    override fun getDisplayName() = "Pyrefly"
    
    override fun createComponent() = panel
    
    override fun isModified() = panel.isModified()
    
    override fun reset() {
        panel.reset()
    }
    
    override fun apply() {
        panel.apply()
        XmlSerializerUtil.copyBean(state, service.state)
        
        openProjects.forEach { project ->
            project.restartNativeServers<PyreflyServerSupportProvider>()
            project.toggleLSP4IJServers(SERVER_ID, restart = pyreflyConfigurations.runningMode == RunningMode.LSP4IJ)
        }
    }
    
}
