abstract class IntSet{
  def incl(x:Int):IntSet
  def contains(x:Int):Boolean
  def union (that:IntSet):IntSet
}

object Empty extends IntSet{
   def incl(x: Int):IntSet = new NonEmpty(x,  Empty,  Empty)
   def contains(x: Int):Boolean = false
   override def toString: String = "."
   override def union(that: IntSet): IntSet = that
}

class NonEmpty (elem: Int, left: IntSet, right: IntSet) extends IntSet {

   def contains(x: Int):Boolean =
    if (x<elem) left contains x
    else if(x>elem) right contains x
    else true

   def incl(x: Int):IntSet =
    if(x<elem) new NonEmpty(elem, left incl x, right)
    else if(x>elem) new NonEmpty(elem, left, right incl x)
    else this

  override def toString: String = "{"+left+elem+right+"}"

  override def union(that: IntSet): IntSet = ((left union right) union that) incl elem
}

println("Welcome to scala worksheet")
val t1 = new NonEmpty(3, Empty,  Empty)
val t2 = t1 incl 4


var x:AnyVal = 1

val a: Array[NonEmpty] = Array(new NonEmpty(1, Empty, Empty))

//val b: Array[IntSet] = a

val l: List[NonEmpty] = List(new NonEmpty(1, Empty, Empty))

val l1: List[IntSet] = l

val list: List[Int] = List(1, 2, 3)
list
