package insyncwithfoo.pyrefly.lsp4ij

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.redhat.devtools.lsp4ij.AbstractDocumentMatcher
import insyncwithfoo.pyrefly.isPythonFile


internal class PyreflyServerDocumentMatcher : AbstractDocumentMatcher() {
    
    override fun match(file: VirtualFile, project: Project) =
        file.isPythonFile
    
}
