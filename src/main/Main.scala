import collections.sseq._

object Main{
  def main(args: Array[String]) = {
    val s: SSeq[Int] = SSeq(1, 2, 3)
    
    def k = (s foldLeft 0)((acc) => (item) => acc + item)
    /*def g = s.fmap{x => x * 2}
    
    def i = for{
      x <- s
      y <- s
    } yield x + y*/
  }
  
  println(k)

}