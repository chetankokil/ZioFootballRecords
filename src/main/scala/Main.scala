import io.circe.generic.auto._
import sttp.client3.circe.asJson
import zio._
import zio.json.{ DeriveJsonCodec, DeriveJsonDecoder }

trait FootBallService {
  def findAllByYear(year: Int): Task[FootBallData]
}

object FootBallService {
  val live: ZLayer[Any, Nothing, Has[FootBallService]] = ZLayer.succeed(new FootBallService {
    override def findAllByYear(year: Int) = Task {
      import sttp.client3._
      val queryMap         = Map("year" -> year, "page" -> 1)
      val uri              = uri"https://jsonmock.hackerrank.com/api/football_matches?$queryMap"
      implicit val backend = HttpURLConnectionBackend()
      val request          = basicRequest.get(uri).response(asJson[FootBallData].getRight)
      val resposnse        = request.send(backend)
      resposnse.body
    }
  })

  def findAllByYear(year: Int): ZIO[Has[FootBallService], Throwable, FootBallData] =
    ZIO.accessM[Has[FootBallService]](_.get.findAllByYear(year))
}

object FootBallDataJson {
  implicit val dataJson            = DeriveJsonCodec.gen[Data]
  implicit val footBallDataJson    = DeriveJsonCodec.gen[FootBallData]
  implicit val footBallJsonDecoder = DeriveJsonDecoder.gen[FootBallData]
}

object Main extends App {

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    FootBallService
      .findAllByYear(2011)
      .provideLayer(FootBallService.live)
      .map(fl => println(fl))
      .exitCode
}
