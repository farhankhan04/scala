import scala.annotation.tailrec

val xs = List(1, 2, 4, 8, 16, 32)

xs.length
xs.last
//xs.init

xs take 3

xs drop 4

xs(3)

xs++List(64, 128, 256)

xs.updated(4,6)

xs indexOf 8

xs indexOf 64

xs contains 8

def init[T](xs: List[T]): List[T] = xs match {
  case List() => throw new Error("init of empty list")
  case List(x) => List()
  case y :: ys => y :: init(ys)
}

init(xs)

def reverse[T](xs:List[T]):List[T] = xs match {
  case List() => xs
  case y::ys => reverse(ys) ++ List(y)
}

def removeAt[T](n: Int, xs: List[T]): List[T] = (xs take n) ++ (xs drop (n+1))

List(List(1,2),List(3,4)).flatten

val x = (3,4,6,6)

val f:(String, String)=>String = {case (key, value)=>key+":"+value}

"adsProgram" indexOf "Program"