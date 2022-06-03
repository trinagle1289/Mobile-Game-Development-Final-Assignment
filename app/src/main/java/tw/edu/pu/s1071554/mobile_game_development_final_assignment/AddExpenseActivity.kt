package tw.edu.pu.s1071554.mobile_game_development_final_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton

// 新增支出界面
class AddExpenseActivity : AppCompatActivity() {

    lateinit var etTime: EditText // 時間文字
    lateinit var etExpense: EditText // 輸入支出金額
    lateinit var etDescribe: EditText // 輸入描述文字

    lateinit var btAdd: ImageButton // 新增按鈕
    lateinit var btBack: ImageButton // 返回按鈕

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_add)

        // 初始化
        etTime = findViewById(R.id.expense_create_time_data)
        etExpense = findViewById(R.id.expense_create_expense_data)
        etDescribe = findViewById(R.id.expense_create_describe_data)
        btAdd = findViewById(R.id.expense_create_add_btn)
        btBack = findViewById(R.id.expense_create_back_btn)
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
        btAdd.setOnClickListener {

        }

        btBack.setOnClickListener {
            var it: Intent = Intent(this, ExpenseListActivity::class.java)
            startActivity(it)
        }
    }

}