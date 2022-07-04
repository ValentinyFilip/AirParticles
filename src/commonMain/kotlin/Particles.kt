import kotlinx.serialization.Serializable

@Serializable
data class Particles(var status: Boolean, var particles: Int, val location: String) {
    val id: Int = location.hashCode()
    var latitude = location.split(",").first()
    var longtitude = location.split(",").last()

    companion object {
        const val path = "/shoppingList"
    }
}
