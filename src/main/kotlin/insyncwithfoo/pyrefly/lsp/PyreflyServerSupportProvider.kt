package insyncwithfoo.pyrefly.lsp

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServer
import com.intellij.platform.lsp.api.LspServerSupportProvider
import com.intellij.platform.lsp.api.LspServerSupportProvider.LspServerStarter
import insyncwithfoo.pyrefly.configurations.RunningMode
import insyncwithfoo.pyrefly.configurations.pyreflyConfigurations
import insyncwithfoo.pyrefly.configurations.pyreflyExecutable
import insyncwithfoo.pyrefly.isPythonFile


@Suppress("UnstableApiUsage")
internal class PyreflyServerSupportProvider : LspServerSupportProvider {
    
    override fun createLspServerWidgetItem(lspServer: LspServer, currentFile: VirtualFile?) =
        WidgetItem(lspServer, currentFile)
    
    override fun fileOpened(project: Project, file: VirtualFile, serverStarter: LspServerStarter) {
        val runningMode = pyreflyConfigurations.runningMode
        
        if (runningMode == RunningMode.LSP && file.isPythonFile) {
            val executable = pyreflyExecutable ?: return
            serverStarter.ensureServerStarted(PyreflyServerDescriptor(project, executable))
        }
    }
    
}
