package week4

abstract class Simulation{
  type Action = () => Unit
  case class Event(time:Int, action:Action)
  private type Agenda = List[Event]
  private var agenda: Agenda = List()
  private var curTime = 0
  def currentTime: Int = curTime;

  def afterDelay(delay: Int)(block: => Unit): Unit = {
    val item = Event(currentTime+delay, () => block)
    agenda = insert(agenda, item)
  }

  def insert(agenda: Agenda, item: Event): Agenda = agenda match {
    case first :: rest if first.time <= item.time =>
      first :: insert(rest, item)
    case _ => item :: agenda
  }

  def loop(): Unit = agenda match {
    case first :: rest =>
      agenda = rest
      curTime = first.time
      first.action()
      loop()
    case Nil =>
  }
  def run(): Unit = {
    afterDelay(0){println("*** simulation started, time = "+currentTime+" ***")}
    loop()
  }

}