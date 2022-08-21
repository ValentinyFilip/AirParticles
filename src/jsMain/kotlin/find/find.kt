package find

import ParticlesItem
import csstype.Cursor
import csstype.Display
import csstype.em
import csstype.px
import editList.InputComponent
import emotion.react.css
import getParticle
import hex
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import mui.icons.material.MapSharp
import react.FC
import react.Props
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div
import showAll.showMore

external interface FindProps : Props {
    var item: ParticlesItem?
    var onChange: (ParticlesItem) -> Unit
    var onDelete: (ParticlesItem) -> Unit
}

private val scope = MainScope()

var find = FC<FindProps> { props ->
    if (props.item != null) {
        div {
            css {
                height = 4.em
                display = Display.block
                paddingTop = 8.px
                paddingRight = 8.px
                paddingBottom = 8.px
                paddingLeft = 32.px
                backgroundColor = hex("#E7E9EB")
            }
            a {
                css {
                    display = Display.block
                }
                +"Sensor: ${props.item!!.location}"
                div {
                    css {
                        width = 20.px
                        cursor = Cursor.pointer
                    }
                    MapSharp()
                    onClick = {
                        var loc = "https://www.google.com/maps/search/?api=1&query=" + props.item!!.location.trim()
                        js("window.open(loc, '_blank', 'noopener,noreferrer')")
                    }
                }
            }
            a {
                css {
                    display = Display.block
                }
                +"Reading: ${props.item!!.particles}"
            }
        }
        showMore {
            editMore = false
            item = props.item!!
            onDelete = { change ->
                props.onDelete(change)
            }
        }
    }

    InputComponent {
        onSubmit = { input ->
            js("console.log(input)")
            scope.launch {
                props.onChange(getParticle(input))
            }
        }
    }
}