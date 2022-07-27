package showAll

import ParticlesItem
import csstype.Display
import csstype.Position.Companion.relative
import csstype.em
import csstype.px
import emotion.react.css
import hex
import react.FC
import react.Props
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div

external interface ShowAllProps : Props {
    var edit: Boolean
    var particlesList: List<ParticlesItem>
    var selectedItem: ParticlesItem?
    var onChange: (ParticlesItem) -> Unit
    var onSelectedItem: (ParticlesItem) -> Unit
}

var showAll = FC<ShowAllProps> { props ->
    for (particle in props.particlesList) {
        div {
            css {
                height = 2.em
                display = Display.block
                paddingTop = 8.px
                paddingRight = 8.px
                paddingBottom = 8.px
                paddingLeft = 32.px
                backgroundColor = hex("#E7E9EB")
                hover {
                    backgroundColor = hex("#CCCCCC")
                }
                if (particle == props.selectedItem) {
                    backgroundColor = hex("#04AA6D")
                    hover {
                        backgroundColor = hex("#04AA6D")
                    }
                }
            }
            a {
                +"Sensor: ${particle.location}"
            }
            a {
                css {
                    position = relative
                    top = 1.em
                    right = 330.px
                }
                +"Reading: ${particle.particles}"
            }
            onClick = {
                props.onSelectedItem(particle)
            }
        }
        if (particle == props.selectedItem) {
            showMore {
                editMore = props.edit
                item = particle
                onChange = { change ->
                    props.onChange(change)
                }
            }
        }
    }
}