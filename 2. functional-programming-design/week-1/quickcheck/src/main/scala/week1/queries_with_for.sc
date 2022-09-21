case class Book(title: String, authors: List[String])

val books = Set(Book(title = "Effective Java", authors = List("Joshua Bloch", "Martin Odersky")),
  Book(title = "Scala for the impatient", authors = List("Joshua Bloch", "Cinnamon Toast Ken")),
  Book(title = "Scala for the patient", authors = List("Joshua Bloch")))

books.tail.head.title

books.head.authors

// to find all the names of the authors who have written at least 2 books
for {
    b1 <- books
    b2 <- books
    if b1 != b2
    if b1.title < b2.title
    a1 <- b1.authors
    a2 <- b2.authors
    if(a1 == a2)
  } yield a1