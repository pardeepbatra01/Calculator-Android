package cdn.kotlincalculator

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.actionAdd
import kotlinx.android.synthetic.main.activity_main.actionBack
import kotlinx.android.synthetic.main.activity_main.actionDivide
import kotlinx.android.synthetic.main.activity_main.actionEquals
import kotlinx.android.synthetic.main.activity_main.actionMinus
import kotlinx.android.synthetic.main.activity_main.actionMultiply
import kotlinx.android.synthetic.main.activity_main.answer
import kotlinx.android.synthetic.main.activity_main.clear
import kotlinx.android.synthetic.main.activity_main.closeBracket
import kotlinx.android.synthetic.main.activity_main.num0
import kotlinx.android.synthetic.main.activity_main.num1
import kotlinx.android.synthetic.main.activity_main.num2
import kotlinx.android.synthetic.main.activity_main.num3
import kotlinx.android.synthetic.main.activity_main.num4
import kotlinx.android.synthetic.main.activity_main.num5
import kotlinx.android.synthetic.main.activity_main.num6
import kotlinx.android.synthetic.main.activity_main.num7
import kotlinx.android.synthetic.main.activity_main.num8
import kotlinx.android.synthetic.main.activity_main.num9
import kotlinx.android.synthetic.main.activity_main.numDot
import kotlinx.android.synthetic.main.activity_main.placeholder
import kotlinx.android.synthetic.main.activity_main.startBracket
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickListners()
    }

    private fun clickListners() {
        //Numbers
        num0.setOnClickListener { appendVal("0", false) }
        num1.setOnClickListener { appendVal("1", false) }
        num2.setOnClickListener { appendVal("2", false) }
        num3.setOnClickListener { appendVal("3", false) }
        num4.setOnClickListener { appendVal("4", false) }
        num5.setOnClickListener { appendVal("5", false) }
        num6.setOnClickListener { appendVal("6", false) }
        num7.setOnClickListener { appendVal("7", false) }
        num8.setOnClickListener { appendVal("8", false) }
        num9.setOnClickListener { appendVal("9", false) }
        numDot.setOnClickListener { appendVal(".", false) }
        //Operators
        clear.setOnClickListener { appendVal("", true) }
        startBracket.setOnClickListener { appendVal(" ( ", false) }
        closeBracket.setOnClickListener { appendVal(" ) ", false) }
        actionDivide.setOnClickListener { appendVal(" / ", false) }
        actionMultiply.setOnClickListener { appendVal(" * ", false) }
        actionMinus.setOnClickListener { appendVal(" - ", false) }
        actionAdd.setOnClickListener { appendVal(" + ", false) }

        actionBack.setOnClickListener {
            val expression = placeholder.text.toString()
            if (expression.isNotEmpty()) {
                placeholder.text = expression.substring(0, expression.length - 1)
            }


        }

        actionEquals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(placeholder.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    Toast.makeText(this, "Double", Toast.LENGTH_SHORT).show()
                    answer.text = longResult.toString()
                } else
                    answer.text = result.toString()

            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show();

                Log.d("EXCEPTION", "Message: ${e.message}")
            }

        }
    }

    fun appendVal(string: String, isClear: Boolean) {
        if (isClear) {
            placeholder.text = ""
            answer.text = ""
//          placeholder.append(string)
        } else {
            placeholder.append(string)
        }
    }

}
