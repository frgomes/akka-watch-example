package actors

import akka.actor.Actor

class Worker extends Actor {
  override def receive: Receive = {
    case m @ _ =>
      println(m)
  }
}
