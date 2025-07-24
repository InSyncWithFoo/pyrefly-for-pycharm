package insyncwithfoo.pyrefly.lsp4ij

import com.intellij.openapi.project.Project
import com.redhat.devtools.lsp4ij.client.LanguageClientImpl
import insyncwithfoo.pyrefly.createSettingsObject


internal class PyreflyServerClient(project: Project) : LanguageClientImpl(project) {
    
    override fun createSettings() = project.createSettingsObject()
    
    override fun findSettings(section: String?): Any? {
        val settings = project.createSettingsObject()
        
        return when (section) {
            "python" -> settings.python
            else -> null
        }
    }
    
}
