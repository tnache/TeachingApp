package edu.axa.teachingapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.axa.teachingapp.model.Game
import edu.axa.teachingapp.model.GameField

class MainViewModel : ViewModel() {

    private val gameData = MutableLiveData<Game>()

    init {
        gameData.value = Game(gameField = GameField(), winner = null)
    }

    fun getGame() = gameData

    fun makeTurn(row: Int, column: Int) {
        val game = gameData.value
        game?.makeTurn(row, column)
        gameData.value = game
    }

    fun startNewGame() {
        gameData.value = Game(gameField = GameField(), winner = null)
    }
}
