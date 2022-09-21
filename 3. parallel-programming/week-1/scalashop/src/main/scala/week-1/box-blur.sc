import scala.collection.mutable
import scala.collection.parallel.CollectionConverters.IterableIsParallelizable

print(2+55)

val a = 3

val b  = List(6,8,1,2,3)

def max(xs: List[Int]): Int =
  xs.fold(Integer.MIN_VALUE)(math.max)

print(max(b))

print(b)


def isVowel(c: Char) : Boolean = {
  if(c=='A' || c=='E' || c=='I' || c=='O' || c=='U' )
    true
  else false
}

Array('E', 'P', 'P', 'I', 'L').foldLeft(0)((count, c) => if (isVowel(c)) count+1 else count)

trait Iterator[T] {
  def hasNext: Boolean

  def next(): T

  def foldLeft[S](z: S)(f: (S, T) => S): S = {
    var result = z
    while(hasNext)
      result = f(result,next())
    result
  }
}
trait Traversable[T] {
  def foreach(f: T => Unit): Unit
  def newBuilder: mutable.Builder[T, Traversable[T]]
  def filter(p: T => Boolean): Traversable[T] = {
    val b = newBuilder
    for (x <- this) if(p(x)) b+=x
    b.result
  }
}

val arr = Array(1,2,3)
val arr1 = arr.map(a=>a*3)
print(arr.mkString("Array(", ", ", ")"))


val a1 = Seq(1,3,5).to(mutable.ArrayBuffer).par
val a2 = Seq(2,4,6).to(mutable.ArrayBuffer).par

print(a1)
