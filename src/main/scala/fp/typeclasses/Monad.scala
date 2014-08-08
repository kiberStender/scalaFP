package fp.typeclasses

trait Monad[A] extends Functor[A]{
  def flatMap[B]: (A => Monad[B]) => Monad[B]
  
  def foreach[U]: (A => U) => Unit = (fn) => {
    map(fn)
    ()
  }
}