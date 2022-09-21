import scala.annotation.tailrec
import scala.math.abs

val tolerance = 0.0001

def isCloseEnough(x:Double, y:Double) = {
  (abs(x-y)/x) < tolerance
}

def fixedPoint(f:Double=>Double)(firstGuess:Double): Double = {
  @tailrec
  def iterate(guess:Double):Double = {
    val next = f(guess)
    if(isCloseEnough(next, guess)) next
    else
      iterate(next)
  }
  iterate(firstGuess)
}

fixedPoint(x=>1+x/2)(1.5)

/** square root */
def sqrt(x:Double):Double = {
  @tailrec
  def iterate(guess:Double):Double = {
    val next = (guess+(x/guess))/2
    if(isCloseEnough(guess, next)) next
    else
      iterate(next)
  }
  iterate(1)
}

sqrt(2)

def averageDamp(f:Double=>Double)(x:Double):Double = (x+f(x))/2

/** square root */
def squareRoot(x:Double):Double = {
  fixedPoint(averageDamp(y=>x/y))(1)
}

squareRoot(3)