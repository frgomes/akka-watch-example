package actors

import akka.actor.{Actor, ActorPath, ActorSystem, Props}
import com.typesafe.config.ConfigFactory


class Client extends Actor {
  private val supervisor = context.actorSelection("akka.tcp://test@127.0.0.1:5150/user/Supervisor")

  supervisor ! "Client is alive."

  def receive: Receive = {
    case msg @ _ =>
      println(msg)
  }
}

object Client {
  def main(args: Array[String]): Unit = {
    val app     = "Client"
    val cluster = "test"
    val default = ConfigFactory.load()
    val config  = default.getConfig(app).withFallback(default)

    val system = ActorSystem.create(cluster, config)
    val actor = system.actorOf(Props(new Client), name = app)
    println(ActorPath.fromString(actor.path.toString))
  }
}
