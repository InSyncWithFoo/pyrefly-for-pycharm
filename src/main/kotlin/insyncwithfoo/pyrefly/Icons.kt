package insyncwithfoo.pyrefly

import com.intellij.openapi.util.IconLoader


private fun Icon.loadIcon(path: String) =
    IconLoader.getIcon(path, this::class.java)


@Suppress("unused")
internal object Icon {
    val TINY_16 by lazy { loadIcon("icons/tiny-16.svg") }
    val TINY_16_WHITE by lazy { loadIcon("icons/tiny-16-white.svg") }
}
