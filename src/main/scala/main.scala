@main
def main(): Unit = Engine.init(
  // Configuration
  State(
    100,
    Map(
      "program" -> List(
        Coordinate(3, 3),
        Coordinate(3, 2),
        Coordinate(2, 1),
        Coordinate(1, 1),
        Coordinate(1, 2),
        Coordinate(0, 3),
        Coordinate(1, 3),
      ),
      "scala" -> List(
        Coordinate(1, 0),
        Coordinate(2, 0),
        Coordinate(3, 0),
        Coordinate(4, 1),
        Coordinate(4, 2),
      ),
    ),
  ),
)
