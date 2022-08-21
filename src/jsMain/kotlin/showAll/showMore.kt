package showAll

import ParticlesItem
import csstype.*
import emotion.react.css
import hex
import mui.icons.material.ContentCopy
import react.FC
import react.Props
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div

external interface ShowMoreProps : Props {
    var editMore: Boolean
    var item: ParticlesItem
    var onDelete: (ParticlesItem) -> Unit
}

var showMore = FC<ShowMoreProps> { props ->
    div {
        css {
            display = Display.block
            paddingTop = 8.px
            paddingRight = 8.px
            paddingBottom = 8.px
            paddingLeft = 32.px
            backgroundColor = hex("#CCCCCC")
            height = if (props.editMore) {
                6.em
            } else {
                3.em
            }
        }
        a {
            css {
                position = Position.relative
                right = 20.px
                borderRadius = 8.px
                backgroundColor = hex("#BFBFBF")
                color = hex("#BFBFBF")
                top = 10.px
                if (props.editMore) {
                    color = hex("#000000")
                }
                hover {
                    color = hex("#000000")
                }
            }
            +"ID: ${props.item.id}"
        }
        div {
            css {
                position = Position.relative
                width = 24.px
                right = 20.px
                left = 200.px
                bottom = 22.px
                cursor = Cursor.pointer
            }
            ContentCopy()
            onClick = {
                var copyText = props.item.id
                js("navigator.clipboard.writeText(copyText)")
            }
        }
        a {
            css {
                bottom = 28.px
                position = Position.relative
                left = 250.px
            }
            +"Updated: ${props.item.lastUpdated}"
        }
        if (props.editMore) {
            div {
                a {
                    css {
                        position = Position.relative
                        right = 20.px
                        top = 20.px
                        bottom = 45.px
                        borderRadius = 8.px
                        backgroundColor = hex("#BFBFBF")
                        color = hex("#000000")
                        cursor = Cursor.pointer
                        hover {
                            backgroundColor = hex("#FF033E")
                        }
                    }
                    +"Delete"
                }
                onClick =  {
                    props.onDelete(props.item)
                }
            }
        }
    }
}