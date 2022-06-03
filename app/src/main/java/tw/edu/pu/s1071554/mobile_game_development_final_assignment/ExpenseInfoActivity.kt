package tw.edu.pu.s1071554.mobile_game_development_final_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton

// 支出資訊界面
class ExpenseInfoActivity : AppCompatActivity() {

    lateinit var etTime: EditText // 時間文字
    lateinit var etExpense: EditText // 輸入支出金額
    lateinit var etDescribe: EditText // 輸入描述文字

    lateinit var btBack: ImageButton // 返回按鈕

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_create)

        // 初始化參數
        etTime = findViewById(R.id.expense_create_time_data)
        etExpense = findViewById(R.id.expense_create_expense_data)
        etDescribe = findViewById(R.id.expense_create_describe_data)
        btBack = findViewById(R.id.expense_create_back_btn)

        setView()
    }

    private fun setView() {
        etTime.text.clear()
        etExpense.text.clear()
        etDescribe.text.clear()
    }

    private fun setButton() {
        btBack.setOnClickListener {

        }
    }

}