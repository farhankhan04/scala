trait Expr

case class Number(n:Int) extends Expr

case class Sum (e1:Expr, e2:Expr) extends Expr

case class Var(s:String) extends Expr



def eval(e:Expr):Int = e match {
  case Number(n) => n
  case Sum(e1, e2) => eval(e1)+eval(e2)

}

def show(e:Expr):String = e match {
  case Number(n) => n.toString
  case Sum(e1, e2) => show(e1) + "+" + show(e2)
  case Var(str) => str
}

show(Sum(Number(44), Number(23)))

1 :: List(2,3)

List(1,2) ::: List(3,4)

List(1,2) ::: Nil

1::2::Nil

