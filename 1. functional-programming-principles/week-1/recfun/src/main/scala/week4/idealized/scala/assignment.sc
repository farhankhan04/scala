import scala.annotation.tailrec

abstract class CodeTree
case class Fork(left: CodeTree, right: CodeTree, chars: List[Char], weight: Int) extends CodeTree
case class Leaf(char: Char, weight: Int) extends CodeTree


type Bit = Int

def weight(tree: CodeTree): Int = tree match {
  case Leaf(_, w) => w
  case Fork(_, _, _, w) => w
}

def chars(tree: CodeTree): List[Char] = tree match {
  case Leaf(c, _) => List(c)
  case Fork(_, _, chars, _)=> chars
}

def times(chars: List[Char]): List[(Char, Int)] = {
  @tailrec
  def loop(chars: List[Char], acc: Map[Char, Int]): Map[Char, Int] = {
    chars match {
      case Nil => acc
      case char :: rest =>
        loop(rest, acc + (char -> (acc(char) + 1)))
    }
  }
  loop(chars, Map.empty[Char, Int] withDefaultValue 0).toList
}


def singleton(trees: List[CodeTree]): Boolean = trees.length==1

def insert(x: Leaf, xs: List[Leaf]): List[Leaf] = xs match {
  case List() => List(x)
  case y::ys => if(x.weight<=y.weight) x::xs else y::insert(x, ys)
}

def makeOrderedLeafList(freqs: List[(Char, Int)]): List[Leaf] = {
  if (freqs.isEmpty) List[Leaf]()
  else insert(Leaf(freqs.head._1, freqs.head._2), makeOrderedLeafList(freqs.tail))
}


def compare(x: CodeTree, y: CodeTree): Boolean = x match {
  case Leaf(_, weight) => y match {
    case Leaf(_, w) => weight < w
    case Fork(_, _, _, w) => weight < w
  }
  case Fork(_, _, _, weight)=> y match {
    case Leaf(_, w) =>   weight < w
    case Fork(_, _, _, w) => weight < w
  }
}

def makeCodeTree(left: CodeTree, right: CodeTree): Fork =
  Fork(left, right, chars(left) ::: chars(right), weight(left) + weight(right))

def insertCodeTree(x: CodeTree, xs: List[CodeTree]): List[CodeTree] = xs match {
  case List() => List(x)
  case y::ys => if (compare(x, y)) x::xs else y :: insertCodeTree(x, ys)

}

def combine(trees: List[CodeTree]): List[CodeTree] =
  if (trees.length<2) trees
  else {
    insertCodeTree(makeCodeTree(trees.head, trees.tail.head), trees.tail.tail)
  }

@tailrec
def until(done: List[CodeTree] => Boolean, merge: List[CodeTree] => List[CodeTree])(trees: List[CodeTree]): List[CodeTree] = {
  if(done(trees)) trees
  else until(done, merge)(trees)
}

/*def decode(tree: CodeTree, bits: List[Bit]): List[Char] = {
  def traverse(remaining: CodeTree, bits: List[Bit]): List[Char] = remaining match {
    case Leaf(c, _) if bits.isEmpty => List(c)
    case Leaf(c, _) => c :: traverse(tree, bits)
    case Fork(left, _, _, _) if bits.head == 0 => traverse(left, bits.tail)
    case Fork(_, right, _, _) => traverse(right, bits.tail)
  }

  traverse(tree, bits)
}*/

def presentInLeft(left: CodeTree, current_char: Char): Boolean = {
  left match {
    case Leaf(c, _) => if (c == current_char) true else false
    case Fork(_, _, chars, _)=> if (chars.contains(current_char)) true else false
  }
}

def encode(tree: CodeTree)(text: List[Char]): List[Bit] = {
  @tailrec
  def encodeAcc(tree:CodeTree, currentTree:CodeTree, text:List[Char], acc:List[Bit]):List[Bit] = {
    if (text.isEmpty) acc
    else {
      val current_char = text.head
      currentTree match {
        case Leaf(_, _) => encodeAcc(tree, tree, text.tail, acc)
        case Fork(left, right, _, _) =>
          if (presentInLeft(left, current_char)) encodeAcc(tree, left, text, acc:::List[Bit](0))
          else encodeAcc(tree, right, text, acc ::: List[Bit](1))
      }
    }
  }
  encodeAcc(tree, tree, text, List[Bit]())
}

def decode(tree: CodeTree, bits: List[Bit]): List[Char] =  {
  @tailrec
  def decodeAcc(tree:CodeTree, currentTree: CodeTree, bits:List[Bit], acc: List[Char]):List[Char] = {
    if (bits.isEmpty) currentTree match {
      case Leaf(c, _) => acc ::: List(c)
    }
    else {
      currentTree match {
        case Leaf(c, _) =>  decodeAcc(tree, tree, bits, acc ::: List(c))
        case Fork(l, r, _, _) => ; if (bits.head == 0) decodeAcc(tree, l, bits.tail, acc) else decodeAcc(tree, r, bits.tail, acc)
      }
    }
  }
  decodeAcc(tree, tree, bits, List[Char]())
}

val frenchCode: CodeTree = Fork(Fork(Fork(Leaf('s',121895),Fork(Leaf('d',56269),Fork(Fork(Fork(Leaf('x',5928),Leaf('j',8351),List('x','j'),14279),Leaf('f',16351),List('x','j','f'),30630),Fork(Fork(Fork(Fork(Leaf('z',2093),Fork(Leaf('k',745),Leaf('w',1747),List('k','w'),2492),List('z','k','w'),4585),Leaf('y',4725),List('z','k','w','y'),9310),Leaf('h',11298),List('z','k','w','y','h'),20608),Leaf('q',20889),List('z','k','w','y','h','q'),41497),List('x','j','f','z','k','w','y','h','q'),72127),List('d','x','j','f','z','k','w','y','h','q'),128396),List('s','d','x','j','f','z','k','w','y','h','q'),250291),Fork(Fork(Leaf('o',82762),Leaf('l',83668),List('o','l'),166430),Fork(Fork(Leaf('m',45521),Leaf('p',46335),List('m','p'),91856),Leaf('u',96785),List('m','p','u'),188641),List('o','l','m','p','u'),355071),List('s','d','x','j','f','z','k','w','y','h','q','o','l','m','p','u'),605362),Fork(Fork(Fork(Leaf('r',100500),Fork(Leaf('c',50003),Fork(Leaf('v',24975),Fork(Leaf('g',13288),Leaf('b',13822),List('g','b'),27110),List('v','g','b'),52085),List('c','v','g','b'),102088),List('r','c','v','g','b'),202588),Fork(Leaf('n',108812),Leaf('t',111103),List('n','t'),219915),List('r','c','v','g','b','n','t'),422503),Fork(Leaf('e',225947),Fork(Leaf('i',115465),Leaf('a',117110),List('i','a'),232575),List('e','i','a'),458522),List('r','c','v','g','b','n','t','e','i','a'),881025),List('s','d','x','j','f','z','k','w','y','h','q','o','l','m','p','u','r','c','v','g','b','n','t','e','i','a'),1486387)

val secret: List[Bit] = List(0,0,1,1,1,0,1,0,1,1,1,0,0,1,1,0,1,0,0,1,1,0,1,0,1,1,0,0,1,1,1,1,1,0,1,0,1,1,0,0,0,0,1,0,1,1,1,0,0,1,0,0,1,0,0,0,1,0,0,0,1,0,1)

decode(frenchCode, secret)

val ass = List[(Char, Int)](('a', 8), ('b', 3), ('c',1), ('d', 1), ('e',1), ('f', 1), ('g', 1),('h', 1))

makeOrderedLeafList(ass)
//(until(singleton, combine)(makeOrderedLeafList(ass))).head

combine(combine(combine(combine(combine(combine(combine(makeOrderedLeafList(ass)))))))).head

val assTree = Fork(Leaf('a',8),Fork(Fork(Fork(Leaf('c',1),Leaf('d',1),List('c', 'd'),2),Fork(Leaf('e',1),Leaf('f',1),List('e', 'f'),2),List('c', 'd', 'e', 'f'),4),Fork(Fork(Leaf('g',1),Leaf('h',1),List('g', 'h'),2),Leaf('b',3),List('g', 'h', 'b'),5),List('c', 'd', 'e', 'f', 'g', 'h', 'b'),9),List('a', 'c', 'd', 'e', 'f', 'g', 'h', 'b'),17)

decode(assTree, List[Bit](1,0,0,0,1,0,1,0))

encode(assTree)(List('c', 'e', 'a'))

type CodeTable = List[(Char, List[Bit])]

def add0(l: CodeTable):CodeTable =
  if(l.isEmpty) List()
  else (l.head._1, 0::l.head._2) :: add0(l.tail)

def add1(r: CodeTable):CodeTable =
  if(r.isEmpty)  List()
  else (r.head._1, 1::r.head._2) :: add1(r.tail)


def convert(tree: CodeTree): CodeTable =
  tree match {
    case Fork(left, right, _, _) =>
      val l = convert(left)
      val r = convert(right)
      val l1 = add0(l)
      val l2 = add1(r)
      l1:::l2
    case Leaf(c, _)=> List((c, List[Bit]()))
  }

convert(assTree)