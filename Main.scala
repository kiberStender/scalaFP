trait Functor[+A]{
  def fmap[B]: (A => B) => Functor[B]
  final def map[B]: (A => B) => Functor[B] = fmap
}

trait Monad[+A] extends Functor[A]{
  def >>=[B]: (A => Monad[B]) => Monad[B]
  final def flatMap[B]: (A => Monad[B]) => Monad[B] = >>=
}

trait SSeq[+A] extends Monad[A]{
  def :-:[B >: A](s: B): SSeq[B] = s :-: this
  
  def ++[B >: A](s:SSeq[B]): SSeq[B]
  
  def foldLeft[B](acc: B): (B => A => B) => B = fn => this match {
    case Nel => acc
    case x :-: xs => (xs foldLeft (fn(acc)(x)))(fn)
  }
  
  def fmap[B]: (A => B) => SSeq[B] = (fn) => this match {
    case Nel => Nel
    case x :-: xs => fn(x) :-: (xs fmap fn)
  }
  
  //final def flatten[B] = foldLeft(SSeq[B]())((acc) => (item) => acc ++ item)
  def >>=[B]: (A => SSeq[B]) => SSeq[B] = (fn) => this match {
    case Nel => Nel
    case x :-: xs => fn(x) ++ (xs >>= fn)
  }
}

sealed case class :-:[+A](head: A, tail: SSeq[A]) extends SSeq[A]

case object Nel extends SSeq[Nothing]

object SSeq{
  def apply[A](s: A*): SSeq[A] = if(s.length == 0) Nel else s.head :-: SSeq(s.tail: _*)
}

object Main{
  def main(args: Array[String]) = {
    val s: SSeq[Int] = SSeq(1, 2, 3)
    
    (s foldLeft 0)((acc) => (item) => acc + item)
    def g = s.fmap{x => x * 2}
    def i = for{
      x <- s
      y <- s
    } yield x + y
  }

}
