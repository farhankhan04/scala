import akka.actor.Actor
import akka.event.LoggingReceive

class Counter extends Actor {

  def counter(n: Int): Receive = LoggingReceive {
    case "incr" => context.become(counter(n + 1))

    case "get" =>
      sender ! n
      context.stop(self)
  }

  override def receive: Receive = counter(0)
}