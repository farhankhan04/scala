object Rationals{
  println("Welcome to Rationals")
}

class Rational(x:Int, y: => Int)
{
  require(y>0,"denominator must be positive")

  val num = x
  val den = y

  override def toString: String = x+"/"+y
}

var x = new Rational(2,7)

if (true) true else false

if (true) 1 else false

trait List[T]{
  def isEmpty: Boolean
  def head:T
  def tail:List[T]
}

class Cons[T](val head:T, val tail:List[T]) extends List[T]{
  val isEmpty = false;
}

class Nil[T] extends List[T]{
  def isEmpty = true
  def head = throw new NoSuchElementException
  def tail = throw new NoSuchElementException
}

def singleton[T](elem:T) = new Cons[T](elem, new Nil[T])

singleton[Int](1)

singleton(true)

var a = 3