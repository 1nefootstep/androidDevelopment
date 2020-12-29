package com.example.tictactoe

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {

    private val gameBoard : Array<CharArray> = Array(3){ CharArray(3) }
    private var turn : Char = 'X'
    private var xScore : Int = 0
    private var oScore : Int = 0
    private lateinit var tableLayout: TableLayout
    private lateinit var turnTextView: TextView
    private lateinit var xScoreTextView : TextView
    private lateinit var oScoreTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        tableLayout = findViewById<TableLayout>(R.id.table_layout)
        turnTextView = findViewById<TextView>(R.id.turnTextView)
        xScoreTextView = findViewById<TextView>(R.id.xScoreView)
        oScoreTextView = findViewById<TextView>(R.id.oScoreView)

        startNewGame(true)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            xScore = 0
            oScore = 0
            startNewGame(false)
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun startNewGame(setClickListener: Boolean) {
        turn = 'X'
        turnTextView.text = String.format(resources.getString(R.string.turn), turn)
        xScoreTextView.text = String.format(resources.getString((R.string.score),
                'X', xScore))
        oScoreTextView.text = String.format(resources.getString((R.string.score),
                'O', oScore))

        for (i in gameBoard.indices) {
            for (j in gameBoard.indices) {
                gameBoard[i][j] = ' '
                val cell = ((tableLayout.getChildAt(i) as TableRow)
                                        .getChildAt(j) as TextView)
                cell.text = ""
                if (setClickListener) {
                    cell.setOnClickListener { cellClickListener(i, j) }
                }
            }
        }
    }

    private fun cellClickListener(row: Int, col: Int) {
        if (gameBoard[row][col] == ' ') {
            gameBoard[row][col] = turn
            val cell = ((tableLayout.getChildAt(row) as TableRow)
                    .getChildAt(col) as TextView)
            cell.text = turn.toString()
            turn = if ('X' == turn) 'O' else 'X' // change turn
            turnTextView.text = String.format(resources.getString(R.string.turn), turn)
            checkGameStatus()
        }
    }

    private fun isBoardFull(gameBoard: Array<CharArray>): Boolean {
        for (i in gameBoard.indices) {
            for (j in gameBoard.indices) {
                if (gameBoard[i][j] == ' ') {
                    return false
                }
            }
        }
        return true
    }

    private fun isWinner(gameBoard: Array<CharArray>, playerChar: Char): Boolean {
        for (i in gameBoard.indices) {
            // check columns
            if (gameBoard[0][i] == playerChar && gameBoard[1][i] == gameBoard[0][i]
                    && gameBoard[2][i] == gameBoard[1][i]) {
                return true
            }
            // check rows
            if (gameBoard[i][0] == playerChar && gameBoard[i][1] == gameBoard[i][0]
                    && gameBoard[i][2] == gameBoard[i][1]) {
                return true
            }
        }
        // check diagonals
        if (gameBoard[0][0] == playerChar && gameBoard[1][1] == gameBoard[0][0]
                && gameBoard[2][2] == gameBoard[1][1]) {
            return true
        }
        if (gameBoard[0][2] == playerChar && gameBoard[1][1] == gameBoard[0][2]
                && gameBoard[2][0] == gameBoard[1][1]) {
            return true
        }
        // if all failed to return true, then playerChar isn't the winner yet
        return false
    }

    private fun checkGameStatus() {
        var state: String = ""
        var shouldShowMessage: Boolean = true
        var winner: Char? = null

        if (isWinner(gameBoard, 'X')) {
            state = String.format(resources.getString(R.string.winner), 'X')
            winner = 'X'
        } else if (isWinner(gameBoard, 'O')) {
            state = String.format(resources.getString(R.string.winner), 'O')
            winner = 'O'
        } else if (isBoardFull(gameBoard)) {
            state = resources.getString(R.string.draw)
        } else {
            shouldShowMessage = false
        }

        if (shouldShowMessage) {
            turnTextView.text = state
            val builder = AlertDialog.Builder(this)
            builder.setMessage(state)
            builder.setPositiveButton(android.R.string.ok) { _, _ ->
                if (winner == 'X') {
                    xScore++
                } else if (winner == 'O') {
                    oScore++
                }
                startNewGame(false)
            }
            val dialog = builder.create()
            dialog.show()
        }
    }
}