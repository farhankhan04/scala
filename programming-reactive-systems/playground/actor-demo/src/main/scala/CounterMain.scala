import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.event.LoggingReceive

object Main extends App {
  implicit lazy val system: ActorSystem = ActorSystem("Main")
  val app = system.actorOf(Props[CounterMain])
  app ! "incr"
  app ! "incr"
  app ! "incr"
  app ! "incr"
  app ! "get"
  system.terminate()

}

class CounterMain extends Actor {

  val counter: ActorRef = context.actorOf(Props[Counter], "counter")

  override def receive: Receive = LoggingReceive {
    case count: Int =>
      println("count is: " + count)
      context.stop(self)
    case _: String => counter ! _

  }
}
