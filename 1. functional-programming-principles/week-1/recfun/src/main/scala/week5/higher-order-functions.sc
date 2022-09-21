def squareList(xs: List[Int]):List[Int] =
  xs.map(x=>x*x)

squareList(List(2,5,6,7))


def posElem(xs:List[Int]):List[Int] =
  xs.filter(x=>x>0)

posElem(List(-2,5,-6,7))

def pack[T](xs:List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x :: _ =>
    val (first, rest) = xs.span(y=>y==x)
    first::pack(rest)
}

pack(List(1,1,1,2,2,3,3,3,4,4, 5))

List(1) reduceLeft((x,y)=>x*y)


def concat[T](xs:List[T], ys:List[T]):List[T]=
  (xs foldRight ys)(_ :: _)


concat(List(1,3,4,5), List(4,6,7,8))

val xs = Array(1,3,4,7,8)
xs.head

1 to 4 flatMap(x => (1 to 3) map (y => (x,y)))

1 until 4 map(x => (1 until x) map (y => (x,y)))

1 until 4 flatMap (x => (1 until x) map (y => (x,y)))

(1 until 7 flatMap (x => (1 until x) map (y => (x, y)))).filter(pair=>pair._1<4)


def isSafe(col: Int, queens: List[Int]):Boolean = {
  val row = queens.length
  val queensWithRow = (row -1 to 0 by -1) zip queens
  queensWithRow forall {
    case (r, c) => col !=c && math.abs(col-c)!=row-r
  }
}

(2 to 0 by -1) zip List(0,2,1)

def nQueens(n:Int):Set[List[Int]] = {
  def placeQueens(k:Int):Set[List[Int]]=
  if(k == 0) Set(List[Int]())
  else
    for{
      queens <- placeQueens(k-1)
      col <- 0 until n
      if isSafe(col, queens)
    } yield col::queens
  placeQueens(n)
}

nQueens(4)

def adjust(term:(Int, Double)):(Int,Double) = {
  val (exp, coeff) = term
  exp -> 2*coeff
}

val m = Map[Int, Double](3-> 5.5, 4->6)

m map adjust

List(2,3,4,5) map (x=>x*x)
val x = 1->2

"erewrefa".groupBy(x=>x)


type Word = String

type Occurrences = List[(Char, Int)]

def wordOccurrences(w: Word): Occurrences = {
  w.toLowerCase.groupBy(x=>x).mapValues(x=>x.length).toList.sorted
}

wordOccurrences(List("arerf","drefty").flatten.mkString)

List(1,3,3,6) mkString(",")

"erewrefa".groupBy(x=>x) mkString(", ")

"erewrefa".groupBy(x=>x)

"erewrefa".groupBy(x=>x) map{
  case(key, value) => "\"" +key+"\":"+ value
}

List(1,2,3,4) map (x => 2*x)

val d1 = List(1.0, 2.0, 3.0)
val d2 = List(1.5, 5.0, 3.0)

d1.zip(d2)

(d1.zip(d2) map {
  case (x, y) => x * y
}).product