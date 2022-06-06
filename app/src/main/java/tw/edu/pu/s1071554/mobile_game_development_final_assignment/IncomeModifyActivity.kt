package tw.edu.pu.s1071554.mobile_game_development_final_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton

class IncomeModifyActivity : AppCompatActivity() {

    lateinit var etTime: EditText // 時間文字
    lateinit var etExpense: EditText // 輸入支出金額
    lateinit var etDescribe: EditText // 輸入描述文字

    lateinit var btModify: ImageButton // 修改按鈕
    lateinit var btDelete: ImageButton // 修改按鈕
    lateinit var btBack: ImageButton // 返回按鈕

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_modify)

        // 初始化
        etTime = findViewById(R.id.income_modify_time_data)
        etExpense = findViewById(R.id.income_modify_expense_data)
        etDescribe = findViewById(R.id.income_modify_describe_data)
        btModify = findViewById(R.id.income_modify_modify_btn)
        btDelete = findViewById(R.id.income_modify_del_btn)
        btBack = findViewById(R.id.income_modify_back_btn)
        setView()
        setButton()

    }

    // 設定畫面資料
    private fun setView() {
        etTime.text.clear()
        etExpense.text.clear()
        etDescribe.text.clear()
    }

    // 設定按鈕功能
    private fun setButton() {
        btModify.setOnClickListener {
            finish()
        }

        btDelete.setOnClickListener {
            finish()
        }

        btBack.setOnClickListener {
            finish()
        }
    }
}