import scala.collection.mutable.Map
import scala.io.Source

object PartOne {
//  def main(args: Array[String]): Unit = {
//    val pathToFile = "./resources/testinput"
//    val parsedInput = filterNonHorizontalOrVertical(parseInputFile(pathToFile))
//    println(parsedInput)
//    println("==============================")
//    println(parsedInput(5).getPointsCovered)
//    println("==============================")
//    println(Actions.countOverlapping(Actions.getPointsCovered(parsedInput)))
//  }

  def countOverlapping(list: List[Coordinate]) = {
    val points: Map[Point, Int] = Map()
    list.foreach(coordinate => {
//      Actions.getPointsCovered(coordinate).foreach(point => points.update(point, (points.getOrElse(point, 0) + 1)))
//      Actions.getPointsCovered(coordinate).foreach(point => points.update(point, (points.getOrElse(point, 0) + 1)))
//      coordinate.getPointsCovered.foreach(point => points.update(point, (points.getOrElse(point, 0) + 1)))
    })
    points
  }

  def filterNonHorizontalOrVertical(list: List[Coordinate]) = list.filter(crdnt => crdnt.isVertical || crdnt.isHorizontal)


  def parseInputFile(pathToFile: String) = {
    val lines = Source.fromFile(pathToFile).getLines().map(new Coordinate(_)).toList
    lines
  }
}

class Coordinate(coordinateString: String) {
  private val splitted: List[List[String]] = coordinateString.split(" -> ").toList.map(_.split(",").toList)
  private val _x1: Int = splitted(0)(0).toInt
  private val _y1: Int = splitted(0)(1).toInt
  private val _x2: Int = splitted(1)(0).toInt
  private val _y2: Int = splitted(1)(1).toInt

  def x1 = _x1

  def y1 = _y1

  def x2 = _x2

  def y2 = _y2

  lazy val isVertical: Boolean = (_x1 == _x2)

  lazy val isHorizontal: Boolean = (_y1 == _y2)

  def overlapsWith(coordinate: Coordinate): Boolean = false

  def getPointsCovered: List[Point] = {
    if (isHorizontal && (_x1 > _x2)) (_x1 to _x2).map(new Point(_, _y1)).toList
    else if (isHorizontal && (_x2 > _x1)) (_x2 to _x1).map(new Point(_, _y1)).toList
    else if(isVertical && (_y1 > _y2)) (_y1 to _y2).map(new Point(_x1, _)).toList
    else (_y2 to _y1).map(new Point(_x1, _)).toList
  }


  override def toString: String = s"(${_x1}, ${_y1} | ${_x2}, ${_y2})"
}

case class Point(x: Int, y: Int)
