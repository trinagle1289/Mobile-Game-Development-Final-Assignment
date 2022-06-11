package tw.edu.pu.s1071554.mobile_game_development_final_assignment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

// 新增收入界面
class IncomeAddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_add)
        setView()
    }

    // 初始化UI變數
    private fun setView() {
        // 時間文字
        val etTime: EditText = findViewById(R.id.income_create_time_data)
        val currentDate: String = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(Date())
        etTime.setText(currentDate)

        // 輸入收入金額
        val etIncome: EditText = findViewById(R.id.income_create_income_data)

        // 輸入描述文字
        val etDescribe:EditText = findViewById(R.id.income_create_describe_data)

        // 返回按鈕
        val btBack: ImageButton = findViewById(R.id.income_create_back_btn)
        btBack.setOnClickListener {
            finish()
        }

        // 新增按鈕
        val replyIntent = Intent()
        val btAdd: ImageButton = findViewById(R.id.income_create_add_btn)
        btAdd.setOnClickListener {
            Toast.makeText(this, "press", Toast.LENGTH_SHORT).show()

            if(TextUtils.isEmpty(etIncome.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val time = etTime.text.toString()
                val amount = etIncome.text.toString()
                val description = etDescribe.text.toString()
                replyIntent.putExtra(EXTRA_TIME, time)
                replyIntent.putExtra(EXTRA_AMOUNT, amount)
                replyIntent.putExtra(EXTRA_DESCRIPTION, description)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_TIME = "tw.edu.pu.s1071554.mobile_game_development_final_assignment.TIME";
        const val EXTRA_AMOUNT = "tw.edu.pu.s1071554.mobile_game_development_final_assignment.AMOUNT";
        const val EXTRA_DESCRIPTION = "tw.edu.pu.s1071554.mobile_game_development_final_assignment.DESCRIPTION";
    }
}