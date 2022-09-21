List(1,2,3).map(x=>x*x)

List(3,8,11).scanLeft(100)((s,x)=> s+x)

List(3,8,11).scanRight(100)((s,x)=> s+x)

List(3,8,11).foldRight(100)((s,x)=> s+x)