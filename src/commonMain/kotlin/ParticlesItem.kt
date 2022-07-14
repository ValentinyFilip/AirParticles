import kotlinx.serialization.Serializable

@Serializable
data class ParticlesItem(var lastUpdated: Int, var particles: Int, val location: String) {
    val id: Int = location.hashCode()
    companion object {
        const val path = "/particles"
    }
}
