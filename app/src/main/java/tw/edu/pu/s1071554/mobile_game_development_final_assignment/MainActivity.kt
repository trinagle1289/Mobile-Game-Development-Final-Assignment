package tw.edu.pu.s1071554.mobile_game_development_final_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

// 主介面
class MainActivity : AppCompatActivity() {

    // UI 變數
    lateinit var tvBalance: TextView // 餘額文字
    lateinit var tvIncome: TextView // 收入金額文字
    lateinit var tvExpense: TextView // 支出金額文字

    lateinit var btnIncome: ImageButton // 收入金額按鈕
    lateinit var btnExpense: ImageButton // 支出金額按鈕

    // 變數
    var balance: Int = 0 // 餘額
    var income: Int = 0 // 收入金額
    var expense: Int = 0 // 支出金額

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化
        tvBalance = findViewById(R.id.main_balance_data_text)
        tvIncome = findViewById(R.id.main_income_data_text)
        tvExpense = findViewById(R.id.main_expense_data_text)
        btnIncome = findViewById(R.id.main_income_btn)
        btnExpense = findViewById(R.id.main_expense_btn)
        setView()
        setButton()

    }



    // 設定畫面資料
    private fun setView() {
        tvBalance.text = balance.toString()
        tvIncome.text = income.toString()
        tvExpense.text = expense.toString()
    }

    // 設定按鈕功能
    private fun setButton() {
        btnIncome.setOnClickListener {
            var it = Intent(this, IncomeListActivity::class.java)
            startActivity(it)
        }

        btnExpense.setOnClickListener {
            var it = Intent(this, ExpenseListActivity::class.java)
            startActivity(it)
        }
    }
}