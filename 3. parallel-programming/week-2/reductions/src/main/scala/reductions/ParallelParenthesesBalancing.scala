package reductions

import scala.annotation._
import org.scalameter._

object ParallelParenthesesBalancingRunner {

  @volatile var seqResult = false

  @volatile var parResult = false

  val standardConfig = config(
    Key.exec.minWarmupRuns -> 40,
    Key.exec.maxWarmupRuns -> 80,
    Key.exec.benchRuns -> 120,
    Key.verbose -> true
  ) withWarmer(new Warmer.Default)

  def main(args: Array[String]): Unit = {
    val length = 100000000
    val chars = new Array[Char](length)
    val threshold = 10000
    val seqtime = standardConfig measure {
      seqResult = ParallelParenthesesBalancing.balance(chars)
    }
    println(s"sequential result = $seqResult")
    println(s"sequential balancing time: $seqtime")

    val fjtime = standardConfig measure {
      parResult = ParallelParenthesesBalancing.parBalance(chars, threshold)
    }
    println(s"parallel result = $parResult")
    println(s"parallel balancing time: $fjtime")
    println(s"speedup: ${seqtime.value / fjtime.value}")
  }
}

object ParallelParenthesesBalancing extends ParallelParenthesesBalancingInterface {

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
   */
  def balance(chars: Array[Char]): Boolean = {
    @tailrec
    def balanceAcc(chars: Array[Char], acc: Int):Boolean = {
      if(chars.isEmpty) {
        if (acc == 0)
          true
        else
          false
      }
      else{
        if(chars.head != '(' && chars.head != ')')
          balanceAcc(chars.tail, acc)
        else if(chars.head == '(')
          balanceAcc(chars.tail, acc+1)
        else balanceAcc(chars.tail, acc-1)
      }
    }
    balanceAcc(chars, 0)
  }

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
   */
  def parBalance(chars: Array[Char], threshold: Int): Boolean = {

    def traverse(idx: Int, until: Int, arg1: Int, arg2: Int) /*: ???*/ = {
      ???
    }

    def reduce(from: Int, until: Int) /*: ???*/ = {
      ???
    }

    reduce(0, chars.length) == ???
  }

  // For those who want more:
  // Prove that your reduction operator is associative!

}
