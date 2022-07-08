@file:Suppress("DEPRECATION")

import kotlinx.browser.document
import react.create
import react.dom.render

fun main() {
    val container = document.getElementById("root")?: error("Couldn't find container!")
    document.body!!.appendChild(container)

    render(App.create(), container)
}