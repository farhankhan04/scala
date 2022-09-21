package com.example

import akka.actor.{Actor, ActorRef, Props}

class CounterMain extends Actor {
  val counter: ActorRef = context.actorOf(Props[Counter], "counter")

  counter ! "incr"
  counter ! "incr"
  counter ! "incr"
  counter ! "get"

  override def receive: Receive = {
    case count: Int => print(s"count was $count")
      context.stop(self)
  }
}
