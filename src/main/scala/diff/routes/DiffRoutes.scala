package diff.routes

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import diff.Diff
import akka.http.scaladsl.server.Directives._
import spray.json.DefaultJsonProtocol

case class DiffResponse(original: String, modified: String, diff: String)
case class DiffRequest(original: String, modified: String)

trait DiffFormats extends DefaultJsonProtocol {
  implicit val diffResponseFormat = jsonFormat3(DiffResponse)
  implicit val diffRequestFormat  = jsonFormat2(DiffRequest.apply)
}

trait DiffRoutes extends SprayJsonSupport with DiffFormats {

  val diffRoutes = path("diff") {
    post {
      entity(as[DiffRequest]) { request =>
        val diffString =
          Diff.generate(request.original, request.modified).stringify2
        complete(
          StatusCodes.OK,
          DiffResponse(request.original, request.modified, diffString)
        )
      }
    }
  }

}
