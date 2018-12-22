import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import routing.{GameRouting, RateRouting}

object GameApp extends App {

  val config = ConfigFactory.load()
  val host = config.getString("http.host")
  val port = config.getInt("http.port")

  implicit val system = ActorSystem("game-service")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val routes = new GameRouting ~ new RateRouting

  Http().bindAndHandle(routes.route, host, port) map {
    binding => println(s"Server start on ${binding.localAddress}")
  } recover {
    case ex => println(s"Server could not start on ${host}:${port}", ex.getMessage)
  }

}
