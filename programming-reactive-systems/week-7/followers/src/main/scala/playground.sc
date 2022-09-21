trait A {
  val i: Int
}
case class A1(r: Int) extends A {
  override val i = r
}
val a = A1(4)
println(a.r)
println(a.i)