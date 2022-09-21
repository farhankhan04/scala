val s1 = Stream.cons[Int](1, (2 to 4).toStream)
s1.tail

s1.apply(1)
s1(0)

def expr = {

  val x = {
    print("x"); 1
  }

  lazy val y = {
    print("y"); 2
  }

  def z = {
    print("z"); 3
  }

  z + y + x + z + y + x

}
print(expr)

val s2 = Stream.cons(1, Stream.empty)

(1 to 11 by 2).toStream(3)

11 #:: Stream(2,3,4)

case class Person(age: Int)
{
  def rage:Int = age
}

trait A{
  case class P(r: Int, c: Int)
  case class B(p1: P, p2: P)
}

class A1 extends A {
  val p = P(0, 0)
  val b = B(P(0, 0), P(0, 0))
  val boo = (b.p1 == p)
}
val a = new A1
print(a.boo)