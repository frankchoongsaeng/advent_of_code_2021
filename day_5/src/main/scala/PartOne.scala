import scala.io.Source

object PartOne {
  def main(args: Array[String]): Unit = {
    val pathToFile = "./resources/testinput"
    val parsedInput = filterNonHorizontalOrVertical(parseInputFile(pathToFile))
    println(parsedInput)
  }

  def findOverlapping = {

  }

  def filterNonHorizontalOrVertical(list: List[Coordinate]) = list.filter(crdnt => crdnt.isVertical || crdnt.isHorizontal)


  def parseInputFile(pathToFile: String) = {
    val lines = Source.fromFile(pathToFile).getLines().map(new Coordinate(_)).toList
    lines
  }
}

class Coordinate(coordinateString: String) {
  private val splitted:List[List[String]] = coordinateString.split(" -> ").toList.map(_.split(",").toList)
  private val _x1: String = splitted(0)(0)
  private val _y1: String = splitted(0)(1)
  private val _x2: String = splitted(1)(0)
  private val _y2: String = splitted(1)(1)

  def x1 = _x1

  def y1 = _y1

  def x2 = _x2

  def y2 = _y2

  lazy val isHorizontal: Boolean = (_x1 == _x2)

  lazy val isVertical: Boolean = (_y1 == _y2)

  def overlapsWith(coordinate: Coordinate): Boolean = false

  override def toString: String = s"(${_x1}, ${_y1} | ${_x2}, ${_y2})"
}
