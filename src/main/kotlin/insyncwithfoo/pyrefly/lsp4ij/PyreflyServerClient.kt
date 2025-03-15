package insyncwithfoo.pyrefly.lsp4ij

import com.intellij.openapi.project.Project
import com.redhat.devtools.lsp4ij.client.LanguageClientImpl


internal class PyreflyServerClient(project: Project) : LanguageClientImpl(project) {
    
    override fun createSettings() = Object()
    
}
