def isPrime(n: Int) =
  (2 until n) forall(i=>n%i!=0)

(1 until 5) flatMap (i =>
  (1 until i) filter (j=>isPrime(i+j)) map (j => (i,j)))

(1 until 4) filter(j=>isPrime(4+j)) map (j => (4,j))


val f1 = (x:Int) => x+1

def f2(x:Int) = x+1

trait A{
  def foo:Int
}

class B extends A {
  override def foo = 1
}

val b = new B()
b.foo

f1.apply(2)

List(1,2,3) map f1
List(2,3,4) map f2