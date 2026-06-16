import scala.annotation.tailrec
import scala.io.StdIn.readLine

object Interface {
  private object CONTENT {
    val BOARD_HEADER = "\n--- Board ---\n"
    val MENU_PROMPT = "\n--- Menu ---\n1. New game\n2. Select word\n3. Exit\nOption: "
    val GUESS_PROMPT = "\n--- Guess ---\nGuess: "
    val START_HEADER = "\n--- Start ---\n"
    val COL_PROMPT = "Column: "
    val ROW_PROMPT = "Row: "
    val INVALID_INT = "Please enter a valid number. "
    val INVALID_GUESS = "\nInvalid guess or word not found!"
  }

  @tailrec
  def menu(board: Board, state: State, guessed: Set[Coordinate]): Unit = {
    print(CONTENT.BOARD_HEADER)
    this.board(board, guessed)
    print(CONTENT.MENU_PROMPT)
    readLine() match {
      case "1" => Engine.init(state)
      case "2" => select(board, state, guessed)
      case "3" => sys.exit(0)
      case _ => menu(board, state, guessed)
    }
  }

  private def select(board: Board, state: State, guessed: Set[Coordinate]): Unit = {
    val guess = this.guess()
    if (Engine.check(guess, start(), state.words))
      state.words.get(guess) match {
        case Some(path) => menu(board, state, guessed ++ path)
        case None =>
          print(CONTENT.INVALID_GUESS)
          menu(board, state, guessed)
      }
    else {
      print(CONTENT.INVALID_GUESS)
      menu(board, state, guessed)
    }
  }

  private def guess(): String = {
    print(CONTENT.GUESS_PROMPT)
    readLine().toLowerCase
  }

  private def start(): Coordinate = {
    @tailrec
    def helper(prompt: String): Int = {
      print(prompt)
      readLine().toIntOption match {
        case Some(num) => num
        case None =>
          print(CONTENT.INVALID_INT)
          helper(prompt)
      }
    }

    print(CONTENT.START_HEADER)
    Coordinate(helper(CONTENT.COL_PROMPT), helper(CONTENT.ROW_PROMPT))
  }

  private def board(board: Board, guessed: Set[Coordinate]): Unit = {
    board.grid.zipWithIndex.foreach { case (content, row) =>
      content.zipWithIndex.foreach { case (cell, col) =>
        if (guessed.contains(Coordinate(col, row)))
          print(s"${Console.GREEN}$cell${Console.RESET} ")
        else print(s"$cell ")
      }
      print("\n")
    }
  }
}
