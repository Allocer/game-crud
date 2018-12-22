package routing

import akka.http.scaladsl.server.Directives.{complete, get, pathEndOrSingleSlash, pathPrefix}
import akka.http.scaladsl.server.Route
import services.GameService

import scala.concurrent.ExecutionContext

class RateRouting(implicit executionContext: ExecutionContext) extends Protocols {

  val gameService = new GameService

  val route: Route = {
    pathPrefix("rates") {
      pathEndOrSingleSlash {
        get {
          complete {
            gameService.getGames()
          }
        }
      }
    }
  }
}
