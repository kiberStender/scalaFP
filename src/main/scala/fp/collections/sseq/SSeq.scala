package fp.collections.sseq

import fp.typeclasses.Monad


trait SSeq[+A] extends Monad[A]{
  def :-:[B >: A](s: B): SSeq[B] = s :-: this
  
  def ++[B >: A]: SSeq[B] => SSeq[B]
  
  def foldLeft[B]: B => (B => A => B) => B = acc => fn => this match {
    case Nel => acc
    case x :-: xs => (xs foldLeft (fn(acc)(x)))(fn)
  }
  
  def map[B]: (A => B) => SSeq[B] = (fn) => this match {
    case Nel => Nel
    case x :-: xs => fn(x) :-: (xs map fn)
  }
  
  //final def flatten[B] = foldLeft(SSeq[B]())((acc) => (item) => acc ++ item)
  override def flatMap[B]: (A => Monad[B]) => SSeq[B] = (fn) => this match {
    case Nel => Nel
    case x :-: xs => fn(x) ++ (xs flatMap fn)
  }
}

object SSeq{
  def apply[A](s: A*): SSeq[A] = if(s.length == 0) Nel else s.head :-: SSeq(s.tail: _*)
}

sealed case class :-:[+A](head: A, tail: SSeq[A]) extends SSeq[A]

case object Nel extends SSeq[Nothing]