package u02.exercises.tasks

import u02.Optionals.Option
import u02.Optionals.Option.{None, Some}

import scala.annotation.tailrec
import java.lang.Math.PI
import scala.math.pow

object Task2aFunctions extends App:

  // ============= Step a =============
  def sign(x: Int): String = x match
    case x if x >= 0 => "positive"
    case _ => "negative"

  var sign2: Int => String = x => x match
    case x if x >= 0 => "positive"
    case _ => "negative"

  println(sign(4))
  println(sign(0))
  println(sign(-4))

  println(sign2(4))
  println(sign2(0))
  println(sign2(-4))

  // ============= Step b =============
  def neg(f: String => Boolean): String => Boolean =
    (x: String) => !f(x)

  val empty: String => Boolean = _ == "" // predicate on strings
  val notEmpty = neg(empty) // which type of notEmpty?
  println(notEmpty("foo")) // true
  println(notEmpty("")) // false
  println(notEmpty("foo") && !notEmpty("")) // true.. a comprehensive test

  // ============= Step c =============
  def neg2[X](f: X => Boolean): X => Boolean =
    (x: X) => !f(x)

  val geZero: Integer => Boolean = _ >= 0
  var lessZero = neg2(geZero)

  println(geZero(2)) // true
  println(lessZero(2)) // false
  println(geZero(2) && !lessZero(2)) // true


object Task2bFunctions extends App:

  // ============= Step 4 Currying =============
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


  // ============= Step 5. Composition of functions =============
  def compose(f: Int => Int, g: Int => Int): Int => Int =
    x => f(g(x))

  println(compose(_ - 1, _ * 2)(5))

  def compose2[X, Y, Z](f: Y => Z, g: X => Y): X => Z =
    x => f(g(x))

  println(compose2((x: Int) => x + ", doubled", (y: Int) => y * 2)(5))


object Task3Recursion extends App:
  @tailrec
  def gcdOpt(a: Int, acc: Int): Int = (a, acc) match
    case (_, acc) if acc == 0 => a
    case (a, acc) => gcdOpt(acc, a % acc)

  @tailrec
  def gcd(a: Int, b: Int): Int = (a, b) match
    case (a, b) if a > b => gcd(a - b, b)
    case (a, b) if a < b => gcd(a, b - a)
    case _ => a

  println(gcd(24, 16))
  println(gcdOpt(24, 16))

object Task4Shapes extends App:
  enum Shape:
    // x and y represent the bottom left point of the rectangle/square.
    case Rectangle(x: Double, y: Double, width: Double, height: Double)
    case Square(x: Double, y: Double, side: Double)
    case Circle(centerX: Double, centerY: Double, radius: Double)

  object Shape:
    def perimeter(shape: Shape): Double = shape match
      case Rectangle(_, _, width, height) => 2 * width + 2 * height
      case Circle(_, _, radius) => 2 * PI * radius
      case Square(_, _, side) => 4 * side

    private def inCircle(centerX: Double, centerY: Double, r: Double, p: (Double, Double)): Boolean =
      pow(centerX - p._1, 2) + pow(centerY - p._2, 2) <= pow(r, 2)

    private def inRectangle(x: Double, y:Double, width: Double, height: Double, p: (Double, Double)): Boolean =
      x <= p._1 && y <= p._2 && x + width >= p._1 && y + height >= p._2

    def contains(shape: Shape, p: (Double, Double)): Boolean = shape match
      case Rectangle(x, y, width, height) if inRectangle(x, y, width, height, p) => true
      case Square(x, y, side) if inRectangle(x, y, side, side, p) => true
      case Circle(x, y, r) if inCircle(x, y, r, p) => true
      case _ => false

  import Shape.*

  // Created via TDD. See file Task4Tests

object Task5Optionals extends App:
  object Option:
    def isEmpty[A](opt: Option[A]): Boolean = opt match
      case None() => true
      case _ => false

    def orElse[A, B >: A](opt: Option[A], orElse: B): B = opt match
      case Some(a) => a
      case _ => orElse

    def flatMap[A, B](opt: Option[A])(f: A => Option[B]): Option[B] = opt match
      case Some(a) => f(a)
      case _ => None()

    def filter[A](opt: Option[A])(filter: A => Boolean): Option[A] = opt match
      case Some(a) if filter(a) => opt
      case _ => None[A]()

    def map[A](opt: Option[A])(map: A => Boolean): Option[Boolean] = opt match
      case Some(a) if map(a) => Some(true)
      case Some(a) if !map(a) => Some(false)
      case _ => None[Boolean]()

    def fold[A](opt: Option[A])(default: A)(fold: A => A): A = opt match
      case Some(a) => fold(a)
      case _ => default

  import Option.*

  println(filter(Some(5))(_ > 2))     // Some(5)
  println(filter(Some(5))(_ > 5))     // None
  println(filter(None[Int]())(_ > 2)) // None

  println(map(Some(5))(_ > 2))     // Some(true)
  println(map(Some(5))(_ > 8))     // Some(false)
  println(map(None[Int]())(_ > 2)) // None

  println(fold(Some(5))(1)(_ + 1))     // 6
  println(fold(None[Int]())(1)(_ + 1)) // 1
