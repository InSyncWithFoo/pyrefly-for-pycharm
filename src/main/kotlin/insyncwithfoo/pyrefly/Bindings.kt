package insyncwithfoo.pyrefly

import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.dsl.builder.ButtonsGroup
import com.intellij.ui.dsl.builder.Cell
import com.intellij.ui.dsl.builder.MutableProperty
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.toMutableProperty
import kotlin.reflect.KMutableProperty0


private fun <T> KMutableProperty0<T?>.toNonNullableProperty(getDefaultValue: () -> T): MutableProperty<T> {
    return MutableProperty({ get() ?: getDefaultValue() }, { set(it) })
}


internal fun <C : TextFieldWithBrowseButton> Cell<C>.bindText(
    property: KMutableProperty0<String?>,
    getDefaultValue: () -> String
): Cell<C> {
    return bindText(property.toNonNullableProperty(getDefaultValue))
}


internal inline fun <reified T> ButtonsGroup.bindSelected(property: KMutableProperty0<T>) {
    bind(property.toMutableProperty(), T::class.java)
}
