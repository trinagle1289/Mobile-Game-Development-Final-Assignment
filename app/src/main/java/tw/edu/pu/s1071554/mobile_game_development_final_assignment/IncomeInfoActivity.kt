package tw.edu.pu.s1071554.mobile_game_development_final_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton

// 收入資訊界面
class IncomeInfoActivity : AppCompatActivity() {

    lateinit var etTime: EditText // 時間文字
    lateinit var etIncome: EditText // 輸入收入金額
    lateinit var etDescribe: EditText // 輸入描述文字

    lateinit var btBack: ImageButton // 返回按鈕

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_create)

        // 初始化參數
        etTime = findViewById(R.id.income_create_time_data)
        etIncome = findViewById(R.id.income_create_income_data)
        etDescribe = findViewById(R.id.income_create_describe_data)
        btBack = findViewById(R.id.income_create_back_btn)

        setView()
    }

    private fun setView() {
        etTime.text.clear()
        etIncome.text.clear()
        etDescribe.text.clear()
    }

    private fun setButton() {
        btBack.setOnClickListener {

        }
    }

}