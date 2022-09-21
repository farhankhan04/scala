import org.scalacheck.Gen

import java.util.Random
import org.scalacheck.Gen.const
import quickcheck.{BinomialHeap, QuickCheckHeap}

import scala.util.Try

/** trait Generator */
trait Generator[+T]{
  self =>
  def generate:T

  def map[S](f:T=>S):Generator[S] = new Generator[S] {
    override def generate:S = f(self.generate)
  }

  def flatMap[S](f: T=>Generator[S]):Generator[S] = new Generator[S] {
    override def generate:S = f(self.generate).generate
  }
}

/** Integer generator */
def integers: Generator[Int] = new Generator[Int] {
  val rand = new Random()
  def generate: Int = {
    rand.nextInt()
  }
}

integers.generate

/** boolean generator */
def booleans: Generator[Boolean] = integers.map(x=>x>0)
booleans.generate

val booleanGen:Generator[Boolean] = for {
  x <- integers
} yield x > 0

booleanGen.generate

/** pair generator */
def pairs[T,U](t:Generator[T], u: Generator[U]): Generator[(T, U)] = for {
  x <- t
  y <- u
} yield (x,y)

pairs(integers, booleanGen).generate

/** singleton generator */
def single[T](x:T):Generator[T] = new Generator[T] {
  override def generate: T = x
}

single(5).generate

/** between generator */
def choose(lo:Int, hi:Int): Generator[Int] = for(x <- integers) yield lo + x%(hi-lo)
choose(3, 6).generate

/** one of generator */
def oneOf[T](xs: T*):Generator[T] = for {
  idx <- choose(lo = 0, hi = xs.length)
} yield xs(idx)

oneOf(List(2,4,5,2,1,4)).generate

/** List generator */
lazy val lists:Generator[List[Int]] =
  for {
    isEmpty <- booleans
    list <- if (isEmpty) single(Nil) else for {
      head <- integers
      tail <- lists
    }yield head :: tail
  } yield list

lists.generate

def test[T](generator: Generator[T], numTimes:Int)(f: T => Boolean): Unit = {
  for(_ <- 0 until numTimes){
    val value = generator.generate
    assert(f(value), "test failed for "+value)
  }
  println("Test passed for "+numTimes+" times")
}

for {
  i <- 1 until 5
  j <- if(i%2 == 0) 1 to 2 else -1 to - 2 by -1
} yield (i,j)

test(pairs(lists, lists), 100 ){
  case (xs, ys) => (xs++ys).length >= xs.length
}

def evenNumberGenerator: Generator[Int] = for {
  x <- integers
} yield 2*x


test(evenNumberGenerator, 100)(x => x%2 == 0)

val a = const('c')
a.sample

val smallEvenInteger = Gen.choose(0,200) suchThat (_ % 2 == 0)
smallEvenInteger.sample

class B extends QuickCheckHeap with BinomialHeap

val b = new B
type a = b.H
b.genHeap.sample.head
