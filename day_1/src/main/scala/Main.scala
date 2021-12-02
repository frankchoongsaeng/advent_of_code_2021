import scala.io.Source;

object Main {

  def main(args: Array[String]): Unit = {
    val sourceFile = Source.fromFile("./src/resources/input.txt")
//    println(countIncreasingDepth(sourceFile.getLines()))

    println(countBySlidingWindow(sourceFile.getLines(), 3))
  }

  def countIncreasingDepth(fileIterator: Iterator[String]) = {
    var previousLine = fileIterator.next()
    var counter = 0;

    for (line <- fileIterator) {
      if(line.toInt > previousLine.toInt) counter += 1
      previousLine = line;
    }

    counter
  }



  def countBySlidingWindow(list: Iterator[String], slideDistance: Int) = {
    val listOfSlides = list.sliding(slideDistance).toList.map(s => s.reduce((first, second) => (first.toInt + second.toInt).toString))
    countIncreasingDepth(listOfSlides.iterator)
  }

}
