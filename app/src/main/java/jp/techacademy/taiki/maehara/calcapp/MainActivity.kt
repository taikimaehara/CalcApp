package jp.techacademy.taiki.maehara.calcapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultAdditionButton.setOnClickListener(this)
        resultSubtractionButton.setOnClickListener(this)
        resultMultiplicationButton.setOnClickListener(this)
        resultDivisionButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        try {
            val editText1 = editText1.text.toString().toBigDecimal()
            val editText2 = editText2.text.toString().toBigDecimal()

            Log.d("test", "editText1=$editText1, editText2=$editText2")

            //ボタンに応じた四則演算及び、結果表示
            when (v.id) {
                R.id.resultAdditionButton -> {
                    Log.d("test", "add")

                    displayResult(editText1.add(editText2))
                }
                R.id.resultSubtractionButton -> {
                    Log.d("test", "sub")

                    displayResult(editText1.subtract(editText2))
                }
                R.id.resultMultiplicationButton -> {
                    Log.d("test", "mul")

                    displayResult(editText1.multiply(editText2))
                }
                R.id.resultDivisionButton -> {
                    Log.d("test", "div")

                    try {
                        displayResult(editText1.divide(editText2, 10, BigDecimal.ROUND_HALF_UP))
                    }catch(e: ArithmeticException){

                        Log.d("test","$e")
                        //スナックバー表示前にソフトウェアキーボードを閉じておく
                        val manager =
                            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        manager.hideSoftInputFromWindow(v.windowToken, 0)

                        Snackbar.make(v, "0では割れません", Snackbar.LENGTH_LONG)
                            .setAction("Action", null)
                            .show()
                    }
                }
            }
        }catch(e: NumberFormatException){
            Log.d("test","$e")
            //スナックバー表示前にソフトウェアキーボードを閉じておく
            val manager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(v.windowToken, 0)

            Snackbar.make(v, "数字を入力してください", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }
    }

    private fun displayResult(result: BigDecimal){
        Log.d("test", "$result")
        val intent = Intent(this, ResultCalcActivity::class.java)
        intent.putExtra("VALUE1", result.toString())
        startActivity(intent)
    }

}