println("Hello, World!")


val array = Array(87, 44, 5, 4, 200, 10, 39, 100)

val oddAndSmallPartial: PartialFunction[Int, String] = {
  case x: Int if (x % 2 != 0 && x < 100) => "Odd and less than 100"
}

val evenAndSmallPartial: PartialFunction[Int, String] = {
  case x: Int if (x != 0 && x % 2 == 0 && x < 100) => "Even and less than 100"
}

val negativePartial: PartialFunction[Int, String] = {
  case x: Int if (x < 0) => "Negative Number"
}

val largePartial: PartialFunction[Int, String] = {
  case x: Int if (x > 99) => "Large Number"
}

val zeroPartial: PartialFunction[Int, String] = {
  case x: Int if (x == 0) => "Zero"
}

val result = array groupBy {
  oddAndSmallPartial orElse
    evenAndSmallPartial orElse
    negativePartial orElse
    largePartial orElse
    zeroPartial
}



val lst = List(1, 2, 3)
var history = List[String]()

def addHistory(s: String): Unit = {
  history = history :+ s
}

lst.map {x => addHistory("Doubling %s".format(x)); x * 2}
  .map {x => addHistory("Adding 1 to %s".format(x)); x + 1}

lst
history
