val f: String => String = {
  case "ping" => "pong"
}
f("ping")

type JBinding = (String, Int)

val f1: JBinding => Int = {
  case (_, value) => value
}
f1(("abc", 4))
f1.apply("24234", 5)

val f2: PartialFunction[List[Int], String] = {
  case Nil => "One"
  case _ :: _ :: _ => "two"
}
f2.isDefinedAt(List(1, 2, 3))
f2.isDefinedAt(List(1, 2))

for {
  i <- 1 until 9
  j <- 1 until i
  if (i + j) % 2 == 0
} yield (i, j)