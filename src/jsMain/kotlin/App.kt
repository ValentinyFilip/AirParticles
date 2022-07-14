import csstype.*
import emotion.react.css
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
    var currentLink: Link? by useState(null)

    useEffectOnce {
        scope.launch {
            particles = getParticles()
        }
    }
    div {
        css {
            padding = 0.px
            border = 0.px
        }
        div {
            css {
                height = 100.pct
                width = 200.px
                position = Position.fixed
                paddingTop = 60.px
                backgroundColor = hex("#E7E9EB")
            }
            Navbar {
                links = Links
                selectedLink = currentLink
                onSelectedLink = { link ->
                    currentLink = link
                }
            }
        }
        div {
            if (currentLink?.id == 1) +"test 1"
        }
    }
}