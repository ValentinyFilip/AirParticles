@file:Suppress("DEPRECATION")

import kotlinx.browser.document
import react.create
import react.dom.client.createRoot

data class Link(val id: Int, val name: String)

val Links = listOf<Link>(
    Link(1, "Home"),
    Link(2, "Show all sensors"),
    Link(3, "Find sensor"),
    Link(4, "Edit sensors")
)
fun main() {
    val container = document.getElementById("root") ?: error("Couldn't find container!")

    createRoot(container).render(App.create())
}