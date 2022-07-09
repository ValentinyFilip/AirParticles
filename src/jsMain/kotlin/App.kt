import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.useEffectOnce
import react.useState


private val scope = MainScope()

val App = FC<Props> {
    var particles by useState(emptyList<ParticlesItem>())

    useEffectOnce {
        scope.launch {
            particles = getParticles()
        }
    }
    Navbar {

    }
}