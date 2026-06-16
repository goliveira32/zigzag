case class Generator(seed: Long) {
  def next(): (Char, Generator) = {
    val (num, next) = nextBounded(26)
    ((num + 65).toChar, next)
  }

  private def nextBounded(bound: Int): (Int, Generator) = {
    // Linear Congruential Generator
    val next = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL

    val res = (next >>> 16).toInt % bound
    (if (res < 0) -res else res, Generator(next))
  }
}
