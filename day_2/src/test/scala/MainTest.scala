import org.scalatest.funsuite.AnyFunSuite
import scala.io.Source

class MainTest extends AnyFunSuite {
  test("returns 150 as the depth on the test file") {
    val fileIterator = Source.fromFile("./resources/test.txt").getLines()
    assert(150 == Main.countDistance(fileIterator))
  }

  test("returns 900 as the depth on the test file after sliding through it") {
    val fileIterator = Source.fromFile("./resources/test.txt").getLines()
    assert(900 == Main.countDistanceWithAim(fileIterator))
  }
}
