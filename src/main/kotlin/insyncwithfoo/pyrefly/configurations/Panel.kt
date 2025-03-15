package insyncwithfoo.pyrefly.configurations

import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.dsl.builder.Cell
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.Row
import com.intellij.ui.dsl.builder.panel
import insyncwithfoo.pyrefly.bindSelected
import insyncwithfoo.pyrefly.bindText
import insyncwithfoo.pyrefly.emptyText
import insyncwithfoo.pyrefly.findExecutableInPath
import insyncwithfoo.pyrefly.lsp4ijIsAvailable
import insyncwithfoo.pyrefly.lspIsAvailable
import insyncwithfoo.pyrefly.makeFlexible
import insyncwithfoo.pyrefly.message
import insyncwithfoo.pyrefly.radioButtonFor
import insyncwithfoo.pyrefly.singleFileTextField


private fun Row.executableInput(block: Cell<TextFieldWithBrowseButton>.() -> Unit) =
    singleFileTextField().makeFlexible().apply(block)


private fun Panel.runningModeInputGroup(block: Panel.() -> Unit) =
    buttonsGroup(init = block)


internal fun makePanel(state: PyreflyConfigurations) = panel {
    row(message("configurations.executable.label")) {
        executableInput {
            val detected = findExecutableInPath("pyrefly")?.toString()
            
            bindText(state::executable) { detected.orEmpty() }
            emptyText = detected.orEmpty()
        }
    }
    
    val runningModeInputGroup = runningModeInputGroup {
        row(message("configurations.runningMode.label")) {
            radioButtonFor(RunningMode.DISABLED)
            radioButtonFor(RunningMode.LSP4IJ) { label ->
                message("configurations.runningMode.unavailable", label).takeUnless { lsp4ijIsAvailable }
            }
            radioButtonFor(RunningMode.LSP) { label ->
                message("configurations.runningMode.unavailable", label).takeUnless { lspIsAvailable }
            }
        }
    }
    runningModeInputGroup.bindSelected(state::runningMode)
}
