import kotlinx.serialization.Serializable

@Serializable
data class ParticlesItem(val lastUpdated: Int, val particles: Int, val location: String) {
    val id: Int = location.hashCode()
    companion object {
        const val path = "/particles"
    }
}
