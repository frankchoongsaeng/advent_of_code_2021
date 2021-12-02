import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {
    val fileSource = Source.fromFile("./resources/input")
//    println(countDistance(fileSource.getLines()))
    println(countDistanceWithAim(fileSource.getLines()))
  }

  def countDistance(fileIterator: Iterator[String]) = {
    var horizontal = 0
    var depth = 0

    for(line <- fileIterator) {
      horizontal = if(line.contains("forward")) line.split(" ")(1).toInt + horizontal else horizontal
      depth = if(line.contains("up")) depth - line.split(" ")(1).toInt else if(line.contains("down")) depth + line.split(" ")(1).toInt else depth
    }
    horizontal * depth
  }

  def countDistanceWithAim(fileIterator: Iterator[String]) = {
    var horizontal = 0
    var aim = 0;
    var depth = 0

    for(line <- fileIterator) {
      horizontal = if(line.contains("forward")) line.split(" ")(1).toInt + horizontal else horizontal
      depth = if(line.contains("forward")) depth + (line.split(" ")(1).toInt * aim) else depth
      aim = if(line.contains("up")) aim - line.split(" ")(1).toInt else if(line.contains("down")) aim + line.split(" ")(1).toInt else aim
    }
    horizontal * depth
  }
}
