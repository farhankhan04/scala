package recfun

import scala.annotation.tailrec

object RecFun extends RecFunInterface {

  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(s"${pascal(col, row)} ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int =
    if (c == 0) 1 else if (c == r) 1 else pascal(c - 1, r - 1) + pascal(c, r - 1)

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean ={

  @tailrec
  def count_balance(count: Int, str: List[Char]): Boolean = {
    if (str.isEmpty) count == 0 else if (count < 0) false else {
      if (str.head == '(') count_balance(count + 1, str.tail) else if (str.head == ')') count_balance(count - 1, str.tail) else count_balance(count, str.tail)
    }
  }

  count_balance(0, chars)
}

    /**
     * Exercise 3
     */
    def countChange(money: Int, coins: List[Int]): Int =
      if (coins.isEmpty) 0
      else if (coins.size == 1) {
        if (money % coins.head == 0) 1
        else 0
      }
      else {
        if (coins.head > money)
          countChange(money, coins.tail)
        else countChange(money - coins.head, coins) + countChange(money, coins.tail)
      }
}