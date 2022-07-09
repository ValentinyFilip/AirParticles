import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.a

external interface NavbarProps : Props {
    var name: String
}

// #818181

val NavbarLink = FC<NavbarProps> {
    a {
        css {
            textDecoration = None.none
            fontSize = 2.vh
            display = Display.block
            paddingTop = 8.px
            paddingRight = 8.px
            paddingBottom = 8.px
            paddingLeft = 32.px
            color = hex("#818181")
            hover {
                color = hex("#F1F1F1")
            }
        }
        +it.name
    }
}