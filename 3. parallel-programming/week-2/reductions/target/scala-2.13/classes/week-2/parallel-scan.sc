import reductions.parallel

List(1,3,8).scanRight(100)(_+_)
List(1,3,8).scanLeft(100)(_+_)

List(1,3,8).foldLeft(100)((s, x) => s-x)
((100-1)-3)-8

List(1,3,8).foldRight(100)((s, x) => s-x)
1-(3-(8-100))


def scanLeft[A](inp: Array[A], a0 : A, f: (A,A) => A,
                out : Array[A]):Unit = {
  out(0) = a0
  var a = a0
  var i = 0
  while (i < inp.length){
    a = f(a, inp(i)); i = i+1
    out(i) = a
  }
}

val b = Array(0,0,0,0,0,0)
scanLeft(Array(1,3,5,9,19), 33, (a:Int,b:Int)=>a+b, b)
b

sealed abstract class Tree[A]
case class Leaf[A](a: A) extends Tree[A]
case class Node[A](l: Tree[A], r: Tree[A]) extends Tree[A]

sealed abstract class TreeRes[A] {val res: A}
case class LeafRes[A](override val res: A)extends TreeRes[A]
case class NodeRes[A](l: TreeRes[A], override val res: A, r: TreeRes[A])
  extends TreeRes[A]

def upSweep[A](t: Tree[A], f: (A, A) => A): TreeRes[A] = t match {
  case Leaf(v) => LeafRes(v)
  case Node(l, r) =>
    val (tL, tR) = parallel(upSweep(l,f), upSweep(r,f))
    NodeRes(tL, f(tL.res, tR.res) ,tR)
}

def downSweep[A](t: TreeRes[A], a0: A, f:(A,A) => A) : Tree[A] = t match {
  case LeafRes(a) => Leaf(f(a0, a))
  case NodeRes(l, _, r) =>
    val (tL, tR) = parallel(downSweep(l, a0, f), downSweep(r,f(a0, l.res), f))
    Node(tL, tR)
}

def scanLeft[A](t: Tree[A], a0: A, f:(A,A)=> A): Tree[A] = {
  val tRes = upSweep(t, f)
  val scan1 = downSweep(tRes, a0, f)
  prepend(a0, scan1)
}

def prepend[A](a: A, value: Tree[A]): Tree[A] = {
  case Leaf(v) => Node(Leaf(a), Leaf(v))
  case Node(l, r) => Node(prepend(a, l), r)
}

def mapSeg[A,B](inp: Array[A], left: Int, right: Int, function: (Int, A) => B,
                out: Array[A]): Unit = {
  ???
}

def reduceSeg1[A](inp: Array[A], left: Int, right: Int, a: A,
                  function: (A, A) => A): A = {
  ???
}

def scanLeft[A](inp: Array[A], a0: A, f: (A,A)=> A,
                out: Array[A]): Unit = {
  val fi = {(i:Int, v: A)=> reduceSeg1(inp, 0, i, a0, f)}
  mapSeg(inp, 0, inp.length, fi, out)
  val last = inp.length-1
  out(last+1) = f(out(last), inp(last))
}
