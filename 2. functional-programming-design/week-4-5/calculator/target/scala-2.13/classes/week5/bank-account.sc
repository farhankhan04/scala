import week5.{Signal, Var}

class BankAccount {
  val balance = Var(0)

  def deposit(x: Int): Unit = {
    val curBalance = balance()
    balance() = curBalance + x
  }

  def withdraw(x: Int): Unit = {
    val curBalance = balance()
    balance() = curBalance - x
  }
}

def consolidated(accts: List[BankAccount]): Signal[Int] =
  Signal(accts.map(_.balance()).sum)

val a = new BankAccount()
a deposit 20

val b = new BankAccount()
val total = consolidated(List(a,b))
a deposit 333
b deposit 30
print(s"Total balance: ${total.apply()}")