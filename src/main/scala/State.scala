case class State(seed: Int, words: Map[String, List[Coordinate]]) {
  require(
    words.forall { case (word, path) => word.length == path.length },
    "word length must match its path",
  )
}
