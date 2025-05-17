package insyncwithfoo.pyrefly

import com.intellij.openapi.project.Project


private interface Builder


private operator fun <T : Builder> T.invoke(block: T.() -> Unit) {
    this.apply(block)
}


internal data class Python(
    var pythonPath: String? = null
) : Builder


internal data class Settings(
    val python: Python = Python(),
)


internal fun Project.createSettingsObject() = Settings().apply {
    python {
        pythonPath = interpreterPath?.toString()
    }
}
