import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.*
import io.ktor.server.plugins.cors.CORS
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

val particles = mutableListOf(
    ParticlesItem(true, 15, "49.856017, 18.527613"),
    ParticlesItem(false, 10, "49.854576, 18.548279"),
    ParticlesItem(false, 4, "49.850564, 18.541485"),
    ParticlesItem(false, 25, "49.856197, 18.538224"),
    ParticlesItem(true, 6, "49.857516, 18.565402")
)

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    install(CORS) {
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        anyHost()
    }
    install(Compression) {
        gzip()
    }
    routing {
        get("/testSERVER") {
            call.respondText("SERVER test")
        }
        get("/") {
            call.respondText(
                this::class.java.classLoader.getResource("index.html")!!.readText(),
                ContentType.Text.Html
            )
        }
        route(ParticlesItem.path) {
            get("/testAPI") {
                call.respondText("API test")
            }
            get("/{location}") {
                val location = call.parameters["location"] ?: return@get call.respondText(
                    "Missing location",
                    status = HttpStatusCode.BadRequest
                )
                val item = particles.find { it.location == location } ?: return@get call.respondText(
                    "No sensor with this location: $location",
                    status = HttpStatusCode.NoContent
                )
                call.respond(item)
            }
            get("/getall") {
                call.respond(particles)
            }
            post("/post") {
                particles += call.receive<ParticlesItem>()
                call.respond(HttpStatusCode.OK)
            }
            patch<ParticlesItem>("/patch/{location}") {
                val location = call.parameters["location"] ?: return@patch call.respondText(
                    "Missing location",
                    status = HttpStatusCode.BadRequest
                )
                particles.removeIf { it.location == location }
                particles += call.receive<ParticlesItem>()
            }
            delete("/delete/{location}") {
                val location = call.parameters["location"] ?: return@delete call.respondText(
                    "Missing location",
                    status = HttpStatusCode.BadRequest
                )
                particles.removeIf { it.location == location }
            }
        }
        static("/") {
            resources()
        }
    }
}