package week4.idealized.scala

abstract class Boolean {
  def ifThenElse[T](t: => T, e: => T):T
  def && (x: => Boolean):Any = ifThenElse(x, false)
  def || (x: => Boolean):Any = ifThenElse(true, x)

}

object True extends Boolean{
  override def ifThenElse[T](t: => T, e: => T): T = t
}

object False extends Boolean{
  override def ifThenElse[T](t: => T, e: => T): T = e
}