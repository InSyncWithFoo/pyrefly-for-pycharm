package insyncwithfoo.pyrefly.lsp4ij

import com.intellij.openapi.project.Project
import com.redhat.devtools.lsp4ij.server.ProcessStreamConnectionProvider
import insyncwithfoo.pyrefly.configurations.pyreflyExecutable
import insyncwithfoo.pyrefly.path


internal class PyreflyServerConnectionProvider(commands: List<String>, workingDirectory: String?) :
    ProcessStreamConnectionProvider(commands, workingDirectory) {
    
    companion object {
        fun create(project: Project): PyreflyServerConnectionProvider {
            val executable = pyreflyExecutable!!
            
            val fragments: List<String> = listOf(executable.toString(), "lsp")
            val workingDirectory = project.path?.toString()
            
            return PyreflyServerConnectionProvider(fragments, workingDirectory)
        }
    }
    
}
