package com.example.cf.tictactoe

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //resetBtn.isClickable = false;
        resetBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
    fun btnClick(view: View){
        val btnSelected = view as Button
        var cellID = 0
        when(btnSelected.id){
            R.id.btn1 -> cellID = 1
            R.id.btn2 -> cellID = 2
            R.id.btn3 -> cellID = 3
            R.id.btn4 -> cellID = 4
            R.id.btn5 -> cellID = 5
            R.id.btn6 -> cellID = 6
            R.id.btn7 -> cellID = 7
            R.id.btn8 -> cellID = 8
            R.id.btn9 -> cellID = 9
        }
        //Toast.makeText(this,"Cell ID:"+cellID,Toast.LENGTH_LONG).show()
        playGame(cellID,btnSelected)
    }
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    var activePlayer = 1
    var btnUsed = 0
    private fun playGame(cellID: Int, btnSelected: Button){
        if(activePlayer == 1){
            btnSelected.text = "X"
            btnSelected.setBackgroundColor(Color.parseColor("#6f9fed"))
            player1.add(cellID)
            activePlayer =2
            autoPlay()
        }else{

            btnSelected.text = "O"
            btnSelected.setBackgroundColor(Color.parseColor("#edbb50"))
            player2.add(cellID)
            activePlayer =1

        }
        btnSelected.isEnabled = false


        checkWinner()
    }
    private fun checkWinner(){
        var winner = -1

        //row1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        }
        //row2
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        }
        //row3
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner = 2
        }

        //col1
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        }
        //col2
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner = 2
        }
        //col3
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner = 2
        }

        // "\"
        if(player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner = 2
        }
        // "/"
        if(player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner = 1
        }
        if(player2.contains(3) && player2.contains(5) && player2.contains(7)){
            winner = 2
        }


        if(winner != -1){
            if(winner == 1){
                Toast.makeText(this,"Player won the game.",Toast.LENGTH_LONG).show()
                txtView.text = "WINNER!"


            }else{
                Toast.makeText(this,"Computer won the game.",Toast.LENGTH_LONG).show()
                txtView.text = "LOSER!"

            }
            if(winner == 1 || winner == 2){
                disableBtn()
            }
        }
        btnUsed = 0
        for(cellID in 0..9){
            if(player1.contains(cellID) || player2.contains(cellID)){
                btnUsed++
            }
        }
        if(btnUsed >= 8 && winner == -1){
            Toast.makeText(this,"No one won the Game.",Toast.LENGTH_LONG).show()
            txtView.text = "TIE!"
            disableBtn()
        }
    }
    private fun disableBtn(){
        btn1.isEnabled = false
        btn2.isEnabled = false
        btn3.isEnabled = false
        btn4.isEnabled = false
        btn5.isEnabled = false
        btn6.isEnabled = false
        btn7.isEnabled = false
        btn8.isEnabled = false
        btn9.isEnabled = false
    }
    private fun autoPlay(){
        var emptyCells = ArrayList<Int>()
        for(cellID in 1..9){
            if(!((player1.contains(cellID)) || (player2.contains(cellID)))){
                emptyCells.add(cellID)
            }
        }
        val random = Random()
        val randIndex = random.nextInt(emptyCells.size-0)+0
        val cellID = emptyCells[randIndex]

        if(!(emptyCells.isEmpty())){
            val btnSelected: Button
            when(cellID){
                1->btnSelected = btn1
                2->btnSelected = btn2
                3->btnSelected = btn3
                4->btnSelected = btn4
                5->btnSelected = btn5
                6->btnSelected = btn6
                7->btnSelected = btn7
                8->btnSelected = btn8
                9->btnSelected = btn9
                else -> btnSelected = btn1
            }
            playGame(cellID,btnSelected)
        }
    }


}
