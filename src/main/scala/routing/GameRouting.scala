package routing

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import model.Game
import services.GameService
import spray.json.DefaultJsonProtocol

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

trait Protocols extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val gameFormat = jsonFormat4(Game)
}

class GameRouting(implicit executionContext: ExecutionContext) extends Protocols {

  val gameService = new GameService

  val route: Route = {
    pathPrefix("games") {
      pathEndOrSingleSlash {
        get {
          complete {
            gameService.getGames()
          }
        } ~
          post {
            entity(as[Game]) { gameForCreate =>
              createGame(gameForCreate)
            }
          }
      } ~
        pathPrefix(LongNumber) { id =>
          get {
            complete {
              gameService.getGame(id)
            }
          } ~ put {
            entity(as[Game]) { gameForUpdate =>
              complete {
                gameService.update(gameForUpdate)
              }

            }
          }
        }
    }
  }

  private def createGame(gameForCreate: Game) = {
    onComplete(gameService.createGame(gameForCreate)) {
      case Success(created) => complete(StatusCodes.Created, created)
      case Failure(exception) => complete(StatusCodes.BadRequest, s"An error: $exception")
    }
  }
}
