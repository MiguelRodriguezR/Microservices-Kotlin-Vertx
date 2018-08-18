package parque.micro

import io.vertx.core.Future
import io.vertx.kotlin.core.json.JsonObject


import io.vertx.reactivex.core.AbstractVerticle
import io.vertx.reactivex.ext.web.Router
import io.vertx.reactivex.ext.web.RoutingContext
import parque.micro.util.sendJson

class MainVerticle : AbstractVerticle(){

    override fun start(startFuture: Future<Void>) {

        val routes: Router = Router.router(vertx)
        routes.get("/saludar/:nombre").handler(this::saludar)
        routes.get("/despedir").handler(this::despedir)

        vertx.createHttpServer()
                .requestHandler(routes::accept)
                .rxListen(8080)
                .subscribe({startFuture.complete()},startFuture::fail)
    }

    fun saludar(rc: RoutingContext){
        val name = rc.pathParam("nombre")
        val json = JsonObject("msj" to "hola $name")

        rc.sendJson(json)

    }

    fun despedir(rc: RoutingContext){

        val json = JsonObject("msg" to "adios")

        rc.sendJson(json)
    }

}
