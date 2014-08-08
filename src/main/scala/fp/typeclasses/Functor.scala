package fp.typeclasses

trait Functor[A]{
  def map[B]: (A => B) => Functor[B]
}