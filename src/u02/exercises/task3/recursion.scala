package u02.exercises.task3

object Recursion extends App:
  def gcd(a: Int, b: Int): Int = (a, b) match
    case (a, b) if a == b => a
    case (a, b) if a > b => gcd(a - b, b)
    case _ => gcd(a, b - a)


  def gcd2(a: Int, b: Int, acc: Int): Int =
    4

  println(gcd(24, 16))
