List(1,3,4,6) map (x=>2*x)

List(1,3,4,6) flatMap(x=>List(2*x))

List(1) flatMap (x=>List(2*x, 3*x))

def f(x:Int):List[Int] = {
  List(2*x, 3*x)
}
f(1)


val succ = (x:Int) => x + 1
val anonfun1 = new Function1[Int, Int] {
  def apply(x: Int) = x+x
}
anonfun1(1)

def f(x:Int) = x+1
f(1)


List(1,2,3).map(x=>x+1)

trait Foo{
  def foo = 2
}

abstract class Planar(x:Int, y:Int){
  def length = x
  def breadth = y
}


type Jbinding  = (String, Int, String)
//val x:Jbinding = ("str", 1, "srt")

class ans extends (Int => Int) {
  override def apply(v1: Int):Int = v1+1;
}
List(1,2,3) map (x=>x+1)

abstract class JSON
 class JObj(e:Int) extends JSON{
   def x = e
 }

val data = new JObj(2)

print(data.x)
