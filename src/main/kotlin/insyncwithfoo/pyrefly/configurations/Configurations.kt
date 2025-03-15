package insyncwithfoo.pyrefly.configurations


import com.intellij.openapi.components.BaseState
import com.intellij.util.xmlb.XmlSerializerUtil
import insyncwithfoo.pyrefly.Labeled
import insyncwithfoo.pyrefly.message


internal fun <S : BaseState> S.copy(): S =
    XmlSerializerUtil.createCopy(this)


internal enum class RunningMode(override val label: String) : Labeled {
    DISABLED(message("configurations.runningMode.disabled")),
    LSP4IJ(message("configurations.runningMode.lsp4ij")),
    LSP(message("configurations.runningMode.lsp"));
}


internal class PyreflyConfigurations : BaseState() {
    var executable by string(null)
    var runningMode by enum(RunningMode.LSP)
}
