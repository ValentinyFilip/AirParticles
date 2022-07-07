import csstype.*
import csstype.FontWeight.Companion.normal
import emotion.react.css
import io.ktor.client.statement.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.*
import react.dom.html.InputType
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ul


private val scope = MainScope()

val App = FC<Props> {
    var particles by useState(emptyList<ParticlesItem>())

    useEffectOnce {
        scope.launch {
            particles = getParticles()
        }
    }

    div {
        css {
            height = 100.vh
            fontWeight = normal
            margin = 0.px
            padding = 0.px
            background = linearGradient(45.deg, rgb(215, 52, 227), rgb(204, 10, 10))
        }

        ul {
            particles.forEach { item ->
                li {
                    key = item.toString()
                    +"sensor at location [${item.location}] is ${if(item.status) "online" + " and is detecting ${item.particles} particles" else "offline" + " and last particles reading was ${item.particles}"} "
                }
            }
        }
    }
}