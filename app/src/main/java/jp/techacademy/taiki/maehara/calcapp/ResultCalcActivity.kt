package jp.techacademy.taiki.maehara.calcapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_result_calc.*

class ResultCalcActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_calc)

        val value1 = intent.getStringExtra("VALUE1")
        Log.d("test", "$value1")
        resultText.text = value1
    }
}