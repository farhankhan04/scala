def mergeSort(xs:List[Int]): List[Int] = {
  val n = xs.length/2
  if(n==0)xs
  else
  {
    def merge(xs:List[Int], ys:List[Int]):List[Int] = (xs, ys) match {
      case (xs, Nil) => xs
      case(Nil, ys) => ys
      case(x :: xs1, y :: ys1) =>
        if(x < y) x::merge(xs1, ys) else y::merge(xs, ys1)
    }
    val(fst, snd) = xs splitAt n
    merge(mergeSort(fst), mergeSort(snd))
  }
}

mergeSort(List(-1, 5, 2, -4, 9, 0))

/** Parameterized merged sort */

def mSort[T](xs:List[T])(lt:(T, T) => Boolean): List[T] = {
  val n = xs.length/2
  if(n==0)xs
  else
  {
    def merge(xs:List[T], ys:List[T]):List[T] = (xs, ys) match {
      case (xs, Nil) => xs
      case(Nil, ys) => ys
      case(x :: xs1, y :: ys1) =>
        if(lt(x,y)) x::merge(xs1, ys) else y::merge(xs, ys1)
    }
    val(fst, snd) = xs splitAt n
    merge(mSort(fst)(lt), mSort(snd)(lt))
  }
}

mSort(List(1,5,-3,-6,0,11))((x:Int,y:Int)=>x<y)

mSort(List("apples", "bananas"))((x,y)=>x.compareTo(y)<0)

(1 to 5 by 2) exists(x => x==4)

val s = "Hello  World!"
val l = List(1,2,3)

l zip s

l flatMap(x=>List(2*x, 3*x))

List(3->'c', 4->'d') map{case (x,y) => (2*x , y)}

val f:PartialFunction[String,String] = {
  case "ping"=> "pong"
}

f.isDefinedAt("abc")
