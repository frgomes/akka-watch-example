package actors

import akka.actor._
import com.typesafe.config.ConfigFactory


case class Encapsulated(msg: Terminated)

class Supervisor(another: ActorRef) extends Actor {
  another ! "Supervisor is wired"

  def receive: Receive = {
    case msg @ Terminated(actor) =>
      println(s"++++++++++ TERMINATED:: ${actor}")
      another forward Encapsulated(msg)
    case msg @ _ =>
      context.watch(sender)
      println(s"++++++++++ WATCH:: ${sender} : ${msg}")
      sender ! "Supervisor is now watching you."
  }
}

class AnotherSupervisor extends Actor {
  def receive: Receive = {
    case msg @ Encapsulated(Terminated(actor)) =>
      println(s"++++++++++ ENCAPSULATED TERMINATED :: ${actor}")
    case msg @ _ =>
      println(msg)
  }
}

object Supervisor {
  def main(args: Array[String]): Unit = {
    val app     = "Supervisor"
    val cluster = "test"
    val default = ConfigFactory.load()
    val config  = default.getConfig(app).withFallback(default)

    val system = ActorSystem.create(cluster, config)
    val another = system.actorOf(Props(new AnotherSupervisor), name = "Another" + app)
    val actor = system.actorOf(Props(new Supervisor(another)), name = app)
    println(ActorPath.fromString(actor.path.toString))
  }
}
