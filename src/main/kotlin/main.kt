import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    val gameBoard = arrayOf(" ".repeat(3).toCharArray(), " ".repeat(3).toCharArray(), " ".repeat(3).toCharArray())
    drawField(gameBoard)

    var turn = 1

    while (turn < 10) {

        val playerOnTurn = if (turn % 2 == 0) 'O' else 'X'

        try {
            print("Enter the coordinates: ");

            val xValue = scanner.nextInt() - 1
            val yValue = scanner.nextInt() - 1

            if (xValue !in 0..2 || yValue !in 0..2) {
                println("Coordinates should be from 1 to 3!")
                continue
            }

            val targetField = gameBoard[xValue][yValue]
            if (targetField != ' ') {
                println("This cell is occupied! Choose another one!")
                continue
            }
            gameBoard[xValue][yValue] = playerOnTurn
            drawField(gameBoard)
            if (determineResultFor(playerOnTurn, gameBoard)) {
                print("$playerOnTurn wins")
                return
            }
            turn++
        } catch (e: Exception) {
            println("You should enter numbers!")
            scanner.nextLine()
            continue
        }

        print("Draw")
    }
}

fun drawField(gameBoard: Array<CharArray>) {
    println("-".repeat(9))
    for (i in 0..2) {
        println("| ${gameBoard[i].joinToString(" ")} |")
    }
    println("-".repeat(9))
}

fun determineResultFor(player: Char, gameBoard: Array<CharArray>): Boolean {
    if (gameBoard[0][0] == player && gameBoard[1][1] == player && gameBoard[2][2] == player || gameBoard[0][2] == player && gameBoard[1][1] == player && gameBoard[2][0] == player) {
        return true
    }
    for (i in 0..2) {
        if (gameBoard[0][i] == player && gameBoard[1][i] == player && gameBoard[2][i] == player) return true
        if (gameBoard[i][0] == player && gameBoard[i][1] == player && gameBoard[i][2] == player) return true
    }
    return false
}