package insyncwithfoo.pyrefly

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.openapi.ui.emptyText
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.Cell
import com.intellij.ui.dsl.builder.Row
import javax.swing.JComponent


internal fun Row.singleFileTextField(): Cell<TextFieldWithBrowseButton> {
    val fileChooserDescriptor = FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor()
    val (project, fileChosen) = Pair(null, null)
    
    return textFieldWithBrowseButton(fileChooserDescriptor, project, fileChosen)
}


internal fun <C : JComponent> Cell<C>.makeFlexible() = apply {
    align(AlignX.FILL)
    resizableColumn()
}


internal var Cell<TextFieldWithBrowseButton>.emptyText: String
    @Deprecated("The getter must not be used.", level = DeprecationLevel.ERROR)
    get() = throw RuntimeException()
    set(value) {
        component.emptyText.text = value
    }


internal interface Labeled {
    val label: String
}


internal fun Row.radioButtonFor(item: Labeled) =
    radioButton(item.label, item)


internal fun Row.radioButtonFor(item: Labeled, getContextDependentLabel: (String) -> String?) =
    radioButton(getContextDependentLabel(item.label) ?: item.label, item)
