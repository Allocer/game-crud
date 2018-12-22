package services

import model.Game

import scala.concurrent.{ExecutionContext, Future}

class GameService()(implicit execution: ExecutionContext) {

  private var games = Seq[Game]()

  def createGame(game: Game): Future[Game] = Future {
    games = games :+ game
    game
  }

  def getGames(): Future[Seq[Game]] = Future {
    games
  }

  def getGame(id: Long): Future[Option[Game]] = Future {
    games.find(_.id.get == id)
  }

  def update(game: Game): Future[Option[Game]] = getGame(game.id.get).flatMap {
    case Some(old) => {
      val updated = Game(old.id, game.name, game.rate, game.gameType)
      games = games.filter(_.id != old.id):+ updated
      Future {
        Some(updated)
      }
    }
    case None => Future.successful(None)
  }
}
