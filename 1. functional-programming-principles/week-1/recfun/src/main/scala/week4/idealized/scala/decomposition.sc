trait Expr{
  def isNumber: Boolean
  def isSum: Boolean
  def numValue: Int
  def leftOp: Expr
  def rightOp: Expr

}

class Number(n:Int) extends Expr{
  override def isNumber = true

  override def isSum = false

  override def numValue = n

  override def leftOp: Expr = throw new Error

  override def rightOp: Expr = throw new Error
}

class Sum(e1:Expr, e2:Expr) extends Expr{
  override def isNumber = false

  override def isSum = true

  override def numValue = throw new Error

  override def leftOp: Expr = e1

  override def rightOp: Expr = e2
}

def eval (e:Expr):Int = {
  if(e.isNumber) e.numValue
  else if(e.isSum) eval(e.leftOp)+eval(e.rightOp)
  else throw new Exception("Unknown expression "+e)
}

eval(new Sum(new Number(3), new Number(4)))

3.isInstanceOf[Int]

1.asInstanceOf[Double]

val nn =  new Number(2)