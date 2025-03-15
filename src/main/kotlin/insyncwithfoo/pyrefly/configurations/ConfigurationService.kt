package insyncwithfoo.pyrefly.configurations


import com.intellij.openapi.components.RoamingType
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.SimplePersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service
import insyncwithfoo.pyrefly.findExecutableInPath
import insyncwithfoo.pyrefly.toPathIfItExists
import java.nio.file.Path


@State(name = "insyncwithfoo.pyrefly", storages = [Storage("pyrefly.xml", roamingType = RoamingType.LOCAL)])
@Service(Service.Level.APP)
internal class PyreflyConfigurationService :
    SimplePersistentStateComponent<PyreflyConfigurations>(PyreflyConfigurations()) {
    
    companion object {
        fun getInstance() = service<PyreflyConfigurationService>()
    }
    
}


internal val pyreflyConfigurations: PyreflyConfigurations
    get() = PyreflyConfigurationService.getInstance().state.copy()


internal val pyreflyExecutable: Path?
    get() = pyreflyConfigurations.executable?.toPathIfItExists() ?: findExecutableInPath("pyrefly")
