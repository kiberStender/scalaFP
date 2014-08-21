package fp.maybe

import fp.typeclasses.Monad

trait Maybe[+A] extends Monad[A] {
  def map[B]: (A => B) => Maybe[B] = f => this match {
    case Nonething => Nonething
    case Just(a) => Just(f(a))
  }
  
  def flatMap[B]: (A => Monad[B]) => Maybe[B] = f => this match {
    case Nonething => Nonething
    case Just(a) => f(a).asInstanceOf[Maybe[B]]
  }
}

case object Nonething extends Maybe[Nothing]

sealed case class Just[+A](a: A) extends Maybe[A]