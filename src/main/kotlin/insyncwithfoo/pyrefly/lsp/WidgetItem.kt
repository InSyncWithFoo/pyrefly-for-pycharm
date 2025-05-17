package insyncwithfoo.pyrefly.lsp

import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServer
import com.intellij.platform.lsp.api.lsWidget.LspServerWidgetItem
import insyncwithfoo.pyrefly.Icon
import insyncwithfoo.pyrefly.configurations.PyreflyConfigurable


internal class WidgetItem(lspServer: LspServer, currentFile: VirtualFile?) :
    LspServerWidgetItem(lspServer, currentFile, Icon.TINY_16_WHITE, PyreflyConfigurable::class.java)
