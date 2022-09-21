def countChange(money: Int, coins: List[Int]): Int = {
  if(coins.isEmpty)
    0
  else if (money == 0)
    1
  else {
    if(coins.head > money)
      countChange(money, coins.tail)
    else
      countChange(money, coins.tail) + countChange(money-coins.head, coins)
  }
}

countChange(250, List(1, 2, 5, 10, 20, 50))