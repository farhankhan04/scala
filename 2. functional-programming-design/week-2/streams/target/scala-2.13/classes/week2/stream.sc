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

trait S