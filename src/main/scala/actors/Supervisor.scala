package actors

import akka.actor._
import com.typesafe.config.ConfigFactory


class Supervisor extends Actor {
  def receive: Receive = {
    case msg @ Terminated( actor) =>
      println(s"TERMINATED:: ${actor}")

    case msg @ _ =>
      context.watch(sender)
      println(s"WATCH:: ${sender} : ${msg}")
      sender ! "Supervisor is now watching you."
  }
}

object Supervisor {
  def main(args: Array[String]): Unit = {
    val app     = "Supervisor"
    val cluster = "test"
    val default = ConfigFactory.load()
    val config  = default.getConfig(app).withFallback(default)

    val system = ActorSystem.create(cluster, config)
    val actor = system.actorOf(Props(new Supervisor), name = app)
    println(ActorPath.fromString(actor.path.toString))
  }
}
