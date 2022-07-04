import kotlinx.browser.document
import react.create
import react.dom.client.createRoot

fun main() {
    val container = document.getElementById("root")
    if (container != null) {
        document.body!!.appendChild(container)
    }

    val welcome = Welcome.create {
        name = "Kotlin/JS"
    }
    if (container != null) {
        createRoot(container).render(welcome)
    }
}