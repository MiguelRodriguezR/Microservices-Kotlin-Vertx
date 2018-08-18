package parque.micro.util

import io.vertx.core.json.JsonObject
import io.vertx.reactivex.ext.web.RoutingContext

fun RoutingContext.sendJson(json:JsonObject){
    response()
            .putHeader("Content-Type", "application/json")
            .end(json.encodePrettily())
}
