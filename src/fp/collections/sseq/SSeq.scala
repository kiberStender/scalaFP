package fp.collections.sseq

import typeclasses.Monad

trait SSeq[+A] extends Monad[A]{
  def :-:[B >: A](s: B): SSeq[B] = s :-: this
  
  def ++[B >: A](s:SSeq[B]): SSeq[B]
  
  def foldLeft[B](acc: B): (B => A => B) => B = fn => this match {
    case Nel => acc
    case x :-: xs => (xs foldLeft (fn(acc)(x)))(fn)
  }
  
  def map[B]: (A => B) => SSeq[B] = (fn) => this match {
    case Nel => Nel
    case x :-: xs => fn(x) :-: (xs map fn)
  }
  
  //final def flatten[B] = foldLeft(SSeq[B]())((acc) => (item) => acc ++ item)
  def flatMap[B]: (A => SSeq[B]) => SSeq[B] = (fn) => this match {
    case Nel => Nel
    case x :-: xs => fn(x) ++ (xs flatMap fn)
  }
}

sealed case class :-:[+A](head: A, tail: SSeq[A]) extends SSeq[A]

case object Nel extends SSeq[Nothing]

object SSeq{
  def apply[A](s: A*): SSeq[A] = if(s.length == 0) Nel else s.head :-: SSeq(s.tail: _*)
}