package tw.edu.pu.s1071554.mobile_game_development_final_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.database.FinancialApplication
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.mvvm.FinancialViewModel
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.mvvm.FinancialViewModelFactory

// 主介面
class MainActivity : AppCompatActivity() {
    // 變數
    var balance: Int = 0 // 餘額
    var income: Int = 0 // 收入金額
    var expense: Int = 0 // 支出金額

    private val financialViewModel: FinancialViewModel by viewModels {
        FinancialViewModelFactory((application as FinancialApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvBalance: TextView = findViewById(R.id.main_balance_data_text)
        val tvIncome: TextView = findViewById(R.id.main_income_data_text)
        val tvExpense: TextView = findViewById(R.id.main_expense_data_text)

        // 載入資料
        financialViewModel.allIncomeData.observe(this) { data ->
            // Update the cached copy of the words in the adapter.
            data.let {
                income = 0
                for (i in it)
                    income += i.amount
                tvIncome.text = income.toString()
                // 餘額文字
                balance = income + expense
                tvBalance.text = balance.toString()
            }
        }

        financialViewModel.allExpenseData.observe(this) { data ->
            data.let {
                expense = 0
                for (i in it)
                    expense -= i.amount
                tvExpense.text = expense.toString()
                // 餘額文字
                balance = income - expense
                tvBalance.text = balance.toString()
            }
        }

        setView()
    }

    // 設定畫面資料
    // 初始化UI變數
    private fun setView() {
        // 收入金額按鈕
        val btnIncome:ImageButton = findViewById(R.id.main_income_btn)
        btnIncome.setOnClickListener {
            val it = Intent(this, IncomeListActivity::class.java)
            startActivity(it)
        }

        // 支出金額按鈕
        val btnExpense: ImageButton = findViewById(R.id.main_expense_btn)
        btnExpense.setOnClickListener {
            val it = Intent(this, ExpenseListActivity::class.java)
            startActivity(it)
        }
    }
}