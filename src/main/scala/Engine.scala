import scala.annotation.tailrec

object Engine {
  def init(state: State): Unit = Interface.menu(generate(state), state, Set())

  def check(guess: String, start: Coordinate, words: Map[String, List[Coordinate]]): Boolean = {
    @tailrec
    def helper(prev: Coordinate, next: List[Coordinate]): Boolean = next match {
      case Nil => true
      case x :: xs =>
        if (Direction.values.exists(_.add(prev) == x)) helper(x, xs)
        else false
    }

    words.get(guess) match {
      case Some(path) if start == path.head => helper(start, path.tail)
      case _ => false
    }
  }

  private def generate(state: State): Board = {
    val required = state.words.flatMap { case (word, path) => path.zip(word.toList) }

    @tailrec
    def helper(coord: Coordinate, gen: Generator, acc: List[Char]): (List[Char], Generator) = {
      if (coord.row >= 5) return (acc.reverse, gen)
      if (coord.col >= 5) return helper(Coordinate(0, coord.row + 1), gen, acc)
      val (symbol, next) = required.get(coord) match {
        case Some(symbol) => (symbol, gen)
        case None => gen.next()
      }
      helper(Coordinate(coord.col + 1, coord.row), next, symbol.toUpper :: acc)
    }

    val (cells, _) = helper(Coordinate(0, 0), Generator(state.seed), Nil)
    Board(cells.grouped(5).toList.map(_.toList))
  }
}
