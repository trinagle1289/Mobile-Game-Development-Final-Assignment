package tw.edu.pu.s1071554.mobile_game_development_final_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.room.Room
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.database.FinancialData

// 主介面
class MainActivity : AppCompatActivity() {
    // 變數
    var balance: Int = 0 // 餘額
    var income: Int = 0 // 收入金額
    var expense: Int = 0 // 支出金額

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        // 載入資料
//        val db = Room.databaseBuilder(
//            applicationContext,
//            FinancialDatabase::class.java, "financial_DB"
//        ).build()
//
//        val fDao = db.financialDataDAO()
//        val incomeData:List<FinancialData> = fDao.loadAllIncomeData()
//        for (i in incomeData)
//            income += i.amount
//
//        val expenseData: List<FinancialData> = fDao.loadAllExpenseData()
//        for (i in expenseData)
//            expense += i.amount
//
//        balance = income - expense

        setView()
    }

    // 設定畫面資料
    // 初始化UI變數
    private fun setView() {
        // 餘額文字
        val tvBalance: TextView = findViewById(R.id.main_balance_data_text)
        tvBalance.text = balance.toString()

        val tvIncome: TextView = findViewById(R.id.main_income_data_text)
        tvIncome.text = income.toString()

        val tvExpense: TextView = findViewById(R.id.main_expense_data_text)
        tvExpense.text = expense.toString()

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