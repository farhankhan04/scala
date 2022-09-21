val l = List(1,2,3)

// Map and filter
for{
  x <- l
  if x%2==1
} yield 2*x

l filter (x => x%2==1) map (x => 2*x)

// Flat map
for {
  x <- l
  y <- List(2*x, 3*x)
} yield y

l flatMap (x => List(2*x,3*x))

val n = 5
for{
  i <- 1 until n
  j <- 1 until i
  k <- 1 until j
  if (i+j+k)%2==0
} yield (i,j,k)

(1 until n) flatMap(
  x => (1 until x).withFilter ( y => (x+y)%2==0).
    map (y => (x,y))
)

(1 until n) flatMap(
  x => (1 until x) flatMap(
    y => (1 until y).withFilter(z => (x+y+z)%2==0)
      .map(z => (x, y, z))
  )
)