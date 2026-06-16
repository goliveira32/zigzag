enum Direction(val offset: Coordinate) {
  case North extends Direction(Coordinate(0, -1))
  case South extends Direction(Coordinate(0, 1))
  case East extends Direction(Coordinate(1, 0))
  case West extends Direction(Coordinate(-1, 0))
  case Northeast extends Direction(Coordinate(1, -1))
  case Northwest extends Direction(Coordinate(-1, -1))
  case Southeast extends Direction(Coordinate(1, 1))
  case Southwest extends Direction(Coordinate(-1, 1))

  def add(coord: Coordinate): Coordinate =
    Coordinate(coord.col + this.offset.col, coord.row + this.offset.row)
}
