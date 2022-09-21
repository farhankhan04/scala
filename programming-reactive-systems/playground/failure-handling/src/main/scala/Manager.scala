import akka.actor.SupervisorStrategy.Restart
import akka.actor.{Actor, ActorRef, OneForOneStrategy}

class Manager extends Actor{
  override def receive: Receive = ???
  var restarts: Map[ActorRef, Int] = Map.empty[ActorRef, Int].withDefaultValue(0)
  override val supervisorStrategy = OneForOneStrategy(){
    //case _:  DBException => Restart
  }

}
