import csstype.Display
import csstype.None
import csstype.px
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.a

external interface NavbarProps : Props {
    var links: List<Link>
    var selectedLink: Link?
    var onSelectedLink: (Link) -> Unit
}

val Navbar = FC<NavbarProps> { props ->
    for (link in props.links) {
        a {
            css {
                textDecoration = None.none
                fontSize = 25.px
                display = Display.block
                paddingTop = 8.px
                paddingRight = 8.px
                paddingBottom = 8.px
                paddingLeft = 32.px
                color = hex("#000000")
                backgroundColor = hex("#E7E9EB")
                hover {
                    backgroundColor = hex("#CCCCCC")
                }
                if (link == props.selectedLink) {
                    backgroundColor = hex("#04AA6D")
                    hover {
                        backgroundColor = hex("#04AA6D")
                    }
                }
            }
            +link.name
            onClick = {
                props.onSelectedLink(link)
            }
        }
    }
}