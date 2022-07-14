import io.ktor.http.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*

import kotlinx.browser.window

val endpoint = window.location.origin

val jsonClient = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}

suspend fun getParticles(): List<ParticlesItem> {
    return jsonClient.get(endpoint + ParticlesItem.path + "/getall").body()
}

suspend fun getParticle(particlesItem: ParticlesItem): ParticlesItem {
    return jsonClient.get(endpoint + ParticlesItem.path + "/${particlesItem.location}").body()
}

suspend fun addParticle(particlesItem: ParticlesItem) {
    jsonClient.post(endpoint + ParticlesItem.path) {
        contentType(ContentType.Application.Json)
        setBody(particlesItem)
    }
}

suspend fun deleteParticle(particlesItem: ParticlesItem) {
    jsonClient.delete(endpoint + ParticlesItem.path + "/delete" + "/${particlesItem.location}")
}