import org.scalatest.funsuite.AnyFunSuite
import scala.io.Source

class MainTest extends AnyFunSuite {
  test("returns 7 as the depth on the test file") {
    val fileIterator = Source.fromFile("./src/resources/testinput.txt").getLines()
    assert(7 == Main.countIncreasingDepth(fileIterator))
  }

  test("returns 5 as the depth on the test file after sliding through it") {
    val fileIterator = Source.fromFile("./src/resources/testinput.txt").getLines()
    assert(5 == Main.countBySlidingWindow(fileIterator, 3))
  }
}
