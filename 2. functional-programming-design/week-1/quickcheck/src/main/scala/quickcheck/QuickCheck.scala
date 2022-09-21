package quickcheck

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

import scala.annotation.tailrec

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = oneOf (
    const(empty),
    for {
    x <- arbitrary[A]
    h <- genHeap
  } yield insert(x, h)
  )

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h:H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("gen2") = forAll { (n:A) =>
    deleteMin(insert(n, empty)) == empty
  }

  property("gen3") = forAll {  (h1:H, h2:H) =>
      Math.min(findMin(h1), findMin(h2)) == findMin(meld(h1,h2))
  }

  property("gen4") = forAll { (n1:A, n2:A) =>
    Math.min(n1, n2) == findMin(insert(n2, insert(n1, empty)))
  }

  property("gen5") = forAll { (h: H) =>
    @tailrec
    def isSorted(h: H): Boolean =
      if (isEmpty(h)) true
      else {
        val m = findMin(h)
        val h2 = deleteMin(h)
        isEmpty(h2) || (m <= findMin(h2) && isSorted(h2))
      }
    isSorted(h)
  }

  lazy val genMap: Gen[Map[Int,Int]] = oneOf(
    const(Map.empty[Int,Int]),
    for {
      k <- arbitrary[Int]
      v <- arbitrary[Int]
      m <- oneOf(const(Map.empty[Int,Int]), genMap)
    } yield m.updated(k, v)
  )
}