abstract class JSON

case class JObj(bindings: Map[String, JSON]) extends JSON
case class JStr(str: String) extends JSON
case class JSeq(elems: List[JSON]) extends JSON
case class JNum(num: Double) extends JSON
case class JBool(b: Boolean) extends JSON
case object JNull extends JSON

val data = JObj(Map(
  "firstName" -> JStr("John"),
  "lastName" -> JStr("Smith"),
  "address" -> JObj(Map(
    "streetAddress" -> JStr("21 2nd street"),
    "state" -> JStr("NY"),
    "postalCode" -> JNum(10021)
  )),
  "phoneNumbers" -> JSeq(List(
    JObj(Map(
      "type" -> JStr("home"), "number"-> JStr("212 555-1234")
    )),
    JObj(Map(
      "type" -> JStr("fax"), "number" -> JStr("646 555-4567")
    ))
  ))
))

val jList = List(data)

for {
  JObj(bindings) <- jList
  JSeq(phones) = bindings("phoneNumbers")
  JObj(phone) <- phones
  JStr(digits) = phone("number")
  if digits startsWith "212"
} yield (bindings("firstName"), bindings("lastName"))


def show(json : JSON):String = json match {
  case JSeq(elems) =>
    "["+ (elems map show mkString ",") + "]"
  case JObj(bindings)=>
    val assocs = bindings map {
      case (key, value) => "\""+key+"\":"+show(value)
    }
    "{"+(assocs mkString ",")+"}"
  case JNum(num) =>
    num.toString
  case JStr(str)=>
    '\"' + str + '\"'
  case JBool(b) =>
    b.toString
  case JNull =>
    "null"
}

show(data)

// classic function
def f(x: Int) = x + 1

//Function1[T, R]
//Function1[Int, Int]
val successor: Int => Int = (x:Int) => x + 1

val anonfun1 = new (Int => Int) {
  override def apply(x: Int): Int = x + 1
}

List(1,2,3) map f
List(1,2,3) map successor
List(1,2,3) map anonfun1

type JBinding = (String, JSON)

val anonfun2 = new (JBinding => String) {
  override def apply(j: JBinding): String = j match {
    case (v1, _) => v1
  }
}

// The argument types of an anonymous function must be fully known.

val anonfun3 = (j: JBinding) => j match  {
  case (v1, _) => v1
}

val f3: JBinding => String = {
  case (key, _) => key
}

anonfun3("anonfun3", JBool(true))

val f1 = (x: (String, JSON)) => x match {
  case (_, _) => "wow"
}

val jStr = JStr("fall")
f1("name",jStr)

val f2: PartialFunction[Int,Int] = { case 1 => 22 }

println(f2.isDefinedAt(1))




trait A[T, R]{
  def apply1(t: T): R
  def apply2(t: T): R
}

class B extends A[Int, Int] {
  override def apply1(t: Int): Int = t+1
  override def apply2(t: Int): Int = t+2
}

val b = new B

b.apply1(333)
b.apply2(333)


class JNot(x: Int) extends JSON{
  def y: Int = 2*x
  def foo(): Int = 2*y
}


val jNot = new JNot(3)
jNot.foo()