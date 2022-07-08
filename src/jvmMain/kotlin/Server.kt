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
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val client = KMongo.createClient().coroutine
val database = client.getDatabase("particles")
val collection = database.getCollection<ParticlesItem>()

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
                call.respond(collection.find(ParticlesItem::location eq location).toList())
            }
            get("/getall") {
                call.respond(collection.find().toList())
            }
            post("/post") {
                collection.insertOne(call.receive())
                call.respond(HttpStatusCode.OK)
            }
            patch<ParticlesItem>("/patch/{location}") {
                val location = call.parameters["location"] ?: return@patch call.respondText(
                    "Missing location",
                    status = HttpStatusCode.BadRequest
                )
                val newParticles = call.receive<ParticlesItem>()
                collection.updateOne(ParticlesItem::location eq location, setValue(ParticlesItem::particles, newParticles.particles))
                call.respond(HttpStatusCode.OK)
            }
            delete("/delete/{location}") {
                val location = call.parameters["location"] ?: return@delete call.respondText(
                    "Missing location",
                    status = HttpStatusCode.BadRequest
                )
                collection.deleteOne(ParticlesItem::location eq location)
                call.respond(HttpStatusCode.OK)
            }
        }
        static("/") {
            resources()
        }
    }
}