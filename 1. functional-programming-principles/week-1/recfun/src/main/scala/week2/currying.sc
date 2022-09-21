import scala.annotation.tailrec

/*def sumFactorials(a:Int, b:Int): Int = {
  if(a>b) 0 else fact(a)+sumFactorials(a+1, b)
}*/

/** factorial function */
def fact(a:Int):Int = {
  @tailrec
  def factAcc(a:Int, acc:Int):Int = {
    if(a==0) acc else factAcc(a-1, acc*a)
  }
  factAcc(a, 1)
}
/** identity function */
def id(a:Int): Int = a

/** cube function */
def cube(a:Int): Int = a*a*a

fact(5)

/*def sum(f: Int => Int, a:Int, b:Int):Int = {
  if(a>b) 0 else f(a)+sum(f, a+1, b)
}*/

/** tail recursive sum function */
/*
def sum(f:Int=>Int, a:Int, b:Int): Int = {
  @tailrec
  def loop(a:Int, acc:Int):Int = {
    if(a>b) acc else loop(a+1, acc+f(a))
  }
  loop(a, 0)
}
*/

/*
def sumFactorials(a:Int, b:Int): Int = sum(fact, a, b)
def sumInts(a:Int, b:Int):Int = sum((x:Int) => x, a, b)
def sumCubes(a:Int, b:Int): Int = sum(x => x*x*x, a, b)
*/


/*
sum(fact, 2, 5)*/

/**function returning functions */
def sum(f: Int=> Int)(a:Int, b:Int):Int = {
    if(a>b)
      0
    else
      f(a)+sum(f)(a+1,b)
}


sum(fact)(3,5)

def product(f:Int=>Int)(a:Int, b: Int):Int = {
  if(a>b)
    1
  else
    f(a) * product(f)(a+1,b)
}


/** factorial */
product(x=>x)(1,4)

def mapReduce(f:Int=>Int, combine:(Int, Int)=>Int, zero:Int)(a:Int, b:Int):Int = {
  if(a>b) zero else combine(f(a), mapReduce(f, combine, zero)(a+1, b))
}
/** factorial */
mapReduce(x=>x, (x,y)=>x*y, 1)(1,6)

/** set */
type FunSet = Int=>Boolean

def contains(s:FunSet, elem:Int):Boolean = s(elem)

contains(x=>x>0, -4)

def filter(s: FunSet, p: Int => Boolean): FunSet = x=>p(x)

def x = filter(x=>x%2==0, x=>x%3==0)

x(4)
val bound =1000
-bound

def forall(s: FunSet, p: Int => Boolean): Boolean = {
  @tailrec
  def iter(a: Int): Boolean = {
    if (a < -bound) true
    else if (contains(s, a) && !p(a)) false
    else iter(a - 1)
  }

  iter(bound)
}

forall(i => i == 2 || i == 4 || i == 5 || i == 6, i => i % 2 == 0)

contains(i => i == 2 || i == 4 || i == 5 || i == 6, 5)

5%2 == 0