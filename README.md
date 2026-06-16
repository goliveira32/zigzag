# ZigZag

ZigZag is a word-guessing game written in Scala 3. The goal is to find hidden words on a 5x5 grid. Each word is embedded as a path of coordinates, and players must correctly identify the word and its starting position to reveal it.

## Getting Started

### Prerequisites

- JDK 11 or higher
- [sbt](https://www.scala-sbt.org/)

### Running the Game

```bash
sbt run
```

## Functional Architecture

The project is designed with a strong emphasis on functional programming principles, avoiding mutable state and side effects in the core logic.

### Key Scala Features & FP Patterns

- **Immutability**: All data models (`State`, `Board`, `Coordinate`, `Generator`) are implemented as `case class`es. State transitions are handled by returning new instances of these classes rather than modifying existing ones.
- **Pure State Generation**: The `Generator` uses a Linear Congruential Generator (LCG) that follows a state-passing style:
  ```scala
  def next(): (Char, Generator)
  ```
  This ensures that random character generation is deterministic and pure.
- **Tail Recursion**: To maintain performance while avoiding mutable loops, `@tailrec` is used extensively for the main game loop (`Interface.menu`), coordinate input validation, and path checking (`Engine.check`).
- **Pattern Matching**: Used as the primary mechanism for flow control and data extraction, particularly when dealing with `Option` and `List` types.
- **Scala 3 Enums**: The `Direction` enum defines the 8 possible movement directions (North, South, East, West, and diagonals) with associated coordinate offsets.
- **Higher-Order Functions**: Logic for board validation and rendering is implemented using `flatMap`, `forall`, and `zipWithIndex`.

## Project Structure

- `main.scala`: Entry point and game configuration.
- `Engine.scala`: Core logic for generating the board and validating word paths.
- `Interface.scala`: Command-line interface and user interaction loop.
- `Generator.scala`: Purely functional pseudo-random character generator.
- `Direction.scala`: Enum defining 2D grid movement.
- `Board.scala` & `Coordinate.scala`: Basic domain models for the game grid.
- `State.scala`: Configuration and word-to-path mapping.
