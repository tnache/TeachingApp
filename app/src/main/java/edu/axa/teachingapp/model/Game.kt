package edu.axa.teachingapp.model

enum class GameState {
    NEW,
    IN_PROGRESS,
    FINISHED
}

enum class Player {
    PLAYER_1,
    PLAYER_2
}

enum class FieldState {
    EMPTY,
    X,
    O
}

class GameField {

    var field: Array<Array<FieldState>> = Array(3) {
        Array(3) {
            FieldState.EMPTY
        }
    }

    init {
        for (row in 0..2) {
            for (column in 0..2) {
                field[row][column] = FieldState.EMPTY
            }
        }
    }

    fun get(row: Int, column: Int): String? {
        return when (field[row][column]) {
            FieldState.EMPTY -> null
            FieldState.X -> "X"
            FieldState.O -> "O"
        }
    }

    fun set(row: Int, column: Int, state: FieldState) {
        field[row][column] = state
    }

    fun hasMoreTurns() : Boolean {
        return field.any { row -> row.any { it == FieldState.EMPTY } }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameField

        if (!field.contentDeepEquals(other.field)) return false

        return true
    }

    override fun hashCode(): Int {
        return field.contentDeepHashCode()
    }
}

data class Game(
    var state: GameState = GameState.NEW,
    var winner: Player?,
    var gameField: GameField,
    var turn: Player = Player.PLAYER_1
) {

    fun makeTurn(row: Int, column: Int): Boolean {
        return when(gameField.hasMoreTurns()) {
            true -> {
                when (gameField.get(row, column)) {
                    null -> {
                        val field = when (turn) {
                            Player.PLAYER_1 -> {
                                turn = Player.PLAYER_2
                                FieldState.X
                            }
                            Player.PLAYER_2 -> {
                                turn = Player.PLAYER_1
                                FieldState.O
                            }
                        }
                        gameField.set(row, column, field)
                        state = GameState.IN_PROGRESS
                        true
                    }
                    else -> false
                }
            }
            else -> {
                state = GameState.FINISHED
                false
            }
        }
    }
}