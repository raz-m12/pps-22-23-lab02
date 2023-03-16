package u02

import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.{BeforeEach, Test}
import u02.exercises.tasks.Task4Shapes.Shape.*

class Task4Tests:

  var rect: Rectangle = null
  var square: Square = null
  var circle: Circle = null
  @BeforeEach
  def init(): Unit =
    rect = Rectangle(0, 0, 10, 20)
    square = Square(0, 0, 10)
    circle = Circle(0, 0, 10)
  @Test
  def pointIsInsideRectangle(): Unit =
    val p: (Double, Double) = (10, 10)
    assertTrue(contains(rect, p))

  @Test
  def pointIsInsideSquare(): Unit =
    val p: (Double, Double) = (10, 10)
    assertTrue(contains(square, p))

  @Test
  def pointIsInsideCircle(): Unit =
    val p: (Double, Double) = (5, 2)
    assertTrue(contains(circle, p))

  @Test
  def computeRectanglePerimeter(): Unit =
    assertEquals(60, perimeter(rect))

  @Test
  def computeSquarePerimeter(): Unit =
    assertEquals(40, perimeter(square))

  @Test
  def computeCirclePerimeter(): Unit =
    assertEquals(62.83185307179586, perimeter(circle))
