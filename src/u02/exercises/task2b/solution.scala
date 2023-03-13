package u02.exercises.task2b

object solution extends App:

  // Step 4
  val p1: (Double, Double, Double) => Boolean = (x, y, z) =>
    x <= y && y == z

  def p2(x: Double, y: Double, z: Double): Boolean =
    x <= y && y == z

  println(p1(1, 2, 2))
  println(p2(1, 2, 2))

  // Currying
  def p3(x: Double)(y: Double)(z: Double): Boolean =
    x <= y && y == z

  println(p3(2)(3)(3))

  var p4: Double => Double => Double => Boolean = x => y => z => x <= y && y == z

  println(p4(2)(3)(3))


  // Step 5. Composition of functions

  def compose(f: Int => Int, g: Int => Int): Int => Int =
    x => f(g(x))

  println(compose(_ - 1, _ * 2)(5))

  // TODO may be improved
  def compose2[X <: Double](f: X => X, g: X => X): X => X =
    x => f(g(x))

  println(compose2(_ - 1, _ * 2)(5.0))

