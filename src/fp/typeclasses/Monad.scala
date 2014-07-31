package fp.typeclasses

trait Monad[+A] extends Functor[A]{
  final def flatMap[B]: (A => Monad[B]) => Monad[B]
}