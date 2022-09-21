trait Task[A]{
  def join: A
}
def task[A](c: => A): Task[A]

val t: Task[Int] = task(2+3)

print(t.join)
print(2+3)