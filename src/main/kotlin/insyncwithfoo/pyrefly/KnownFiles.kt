package insyncwithfoo.pyrefly

import com.intellij.openapi.vfs.VirtualFile


internal val VirtualFile.isPythonFile: Boolean
    get() = extension == "py" || extension == "pyi" || extension == "pyw"
