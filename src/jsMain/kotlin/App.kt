
import csstype.Display
import csstype.Overflow
import csstype.pct
import csstype.px
import editList.edit
import emotion.react.css
import find.find
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import navbar.Navbar
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.useEffectOnce
import react.useState
import showAll.showAll

private val scope = MainScope()

val App = FC<Props> {
    var currentLink: Link by useState(Link(2, "Show all sensors"))
    var particles by useState(emptyList<ParticlesItem>())
    var currentItem: ParticlesItem? by useState(null)
    var searchedItem: ParticlesItem? by useState(null)

    useEffectOnce {
        scope.launch {
            particles = getParticles()
        }
    }

    div {
        css {
            display = Display.flex
            height = 872.px
            padding = 0.px
            border = 0.px
            fontSize = 27.px
        }
        div {
            css {
                height = 100.pct
                overflowX = Overflow.hidden
                width = 200.px
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
            css {
                paddingLeft = 32.px
                paddingTop = 60.px
            }
            if (currentLink.id == 1) home()
            if (currentLink.id == 2) div {
                css {
                    height = 100.pct
                    overflowX = Overflow.hidden
                    width = 500.px
                }
                showAll {
                    edit = false
                    particlesList = particles
                    selectedItem = currentItem
                    onSelectedItem = { item ->
                        currentItem = item
                    }
                }
            }
            if (currentLink.id == 3) div {
                css {
                    height = 100.pct
                    overflowX = Overflow.hidden
                    width = 500.px
                }
                find {
                    item = searchedItem
                    onChange = { input ->
                        searchedItem = input
                    }
                }
            }
            if (currentLink.id == 4) div {
                css {
                    height = 100.pct
                    overflowX = Overflow.hidden
                    width = 500.px
                }
                edit {
                    particlesList = particles
                    selectedItem = currentItem
                    onSelectedItem = { item ->
                        currentItem = item
                    }
                    onChange = { input ->
                        particles = input
                    }
                }
            }
        }
    }
}