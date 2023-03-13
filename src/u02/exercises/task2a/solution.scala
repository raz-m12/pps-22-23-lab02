package u02.exercises.task2a

object Solution extends App:

  // Step a
  def sign(x: Int): String = x match
    case x if x > 0 => "positive"
    case _ => "negative"

  var sign2: Int => String = sign(_)

  println(sign(4))
  println(sign(0))
  println(sign(-4))

  println(sign2(4))
  println(sign2(0))
  println(sign2(-4))

  // Step b
  def neg(f: String => Boolean): String => Boolean =
    (x: String) => !f(x)

  val empty: String => Boolean = _ == "" // predicate on strings
  val notEmpty = neg(empty) // which type of notEmpty?
  println(notEmpty("foo")) // true
  println(notEmpty("")) // false
  println(notEmpty("foo") && !notEmpty("")) // true.. a comprehensive test


  // Step c
  def neg2[X](f: X => Boolean): X => Boolean =
    (x: X) => !f(x)

  val geZero: Integer => Boolean = _ >= 0
  var lessZero = neg2(geZero)

  println(geZero(2))
  println(lessZero(2))
  println(geZero(2) && !lessZero(2))