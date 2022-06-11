package tw.edu.pu.s1071554.mobile_game_development_final_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.database.FinancialData
import java.text.SimpleDateFormat
import java.util.*

// 支出列表介面
class ExpenseListActivity : AppCompatActivity() {

    lateinit var fdata: List<FinancialData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_list)
        setView()
    }

    // 設定畫面資料
    private fun setView() {
        // 初始化UI變數
        // 標題名稱
        val currentDate: String = SimpleDateFormat("dd", Locale.getDefault()).format(Date())
        val tvTitle: TextView = findViewById(R.id.expanse_list_title_text)
        val title = currentDate + "月 收入表"
        tvTitle.text = title

        // 新增名單
        val btAdd: ImageButton = findViewById(R.id.expense_create_add_btn)
        btAdd.setOnClickListener {
            val it = Intent(this, ExpenseAddActivity::class.java)
            startActivity(it)
        }

        // 返回主頁面
        val btBack: ImageButton = findViewById(R.id.expanse_list_back_btn)
        btBack.setOnClickListener {
            finish()
        }

        // 載入資料
    }


}