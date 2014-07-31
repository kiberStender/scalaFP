package typeclasses

trait Functor[+A]{
  final def map[B]: (A => B) => Functor[B]
}