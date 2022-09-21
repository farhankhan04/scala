import week2.Pouring

val problem = new Pouring(Vector(4, 9, 7))
print(problem.moves)
print(problem.pathSets.take(3).head)
print(problem.pathSets.take(3)(1))
print(problem.pathSets.take(3)(2))

print(problem.solution(6))
