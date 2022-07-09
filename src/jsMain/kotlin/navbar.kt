import csstype.*
import csstype.Position.Companion.fixed
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div

val Navbar = FC<Props> {
    div {
        css {
            height = 100.pct
            width = 15.vw + 30.px
            position = fixed
            backgroundColor = NamedColor.black
            overflowX = Overflow.hidden
            paddingTop = 60.px
        }
        NavbarLink {
            name = "Show sensors"
        }
        NavbarLink {
            name = "Find sensor"
        }
        NavbarLink {
            name = "Add sensor"
        }
        NavbarLink {
            name = "Delete sensor"
        }
    }
}

