import java.util.concurrent.{ExecutorService, Executors}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.{Failure, Success}

/*Async Coffee example*/
type Coffee = String


/*def makeCoffee(coffeeDone: Coffee => Unit, onFailure: Exception => Unit): Unit

def makeCoffee2(): Future[Coffee] = {
  val p = Promise[Coffee]
  makeCoffee(
    coffee => p.trySuccess(coffee),
    reason => p.tryFailure(reason)
  )
  p.future
}

def drink(coffee: Coffee): Unit = print("Drinking: " + coffee)
def chatWithColleagues(): Unit = print("chatting\n")


def coffeeBreak(): Unit = {
  val eventuallyCoffee = makeCoffee2()
  eventuallyCoffee.onComplete {
    case Success(coffee) => drink(coffee)
    case Failure(_) => print("Coffee failed")
  }
  chatWithColleagues()
}*/


//coffeeBreak()

print(1)

var b = 8;

def f(): Unit =
print(f())

