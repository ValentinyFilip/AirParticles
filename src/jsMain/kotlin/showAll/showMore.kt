package showAll

import ParticlesItem
import csstype.Display
import csstype.Position
import csstype.em
import csstype.px
import emotion.react.css
import hex
import react.FC
import react.Props
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div

external interface ShowMoreProps : Props {
    var item: ParticlesItem
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
            height = 2.em
        }
        a {
            css {
                position = Position.relative
                right = 20.px
                borderRadius = 10.px
                backgroundColor = hex("#BFBFBF")
                color = hex("#BFBFBF")
                top = 10.px
                hover {
                    color = hex("#000000")
                }
            }
            +"ID: ${props.item.id}"
        }
        a {
            css {
                position = Position.relative
                top = 10.px
                left = 100.px
            }
            +"Updated: ${props.item.lastUpdated}"
        }
    }
}