package tw.edu.pu.s1071554.mobile_game_development_final_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

// 收入列表介面
class IncomeListActivity : AppCompatActivity() {

    // UI 變數
    lateinit var tvTitle: TextView // 標題名稱
    lateinit var btAdd: ImageButton // 新增名單
    lateinit var btBack: ImageButton // 返回主頁面

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_list)

        // 初始化
        tvTitle = findViewById(R.id.income_list_title_text)
        btAdd = findViewById(R.id.income_list_create_item_btn)
        btBack = findViewById(R.id.income_list_back_gtn)
        setView()
        setButton()

    }

    // 設定畫面資料
    private fun setView() {

    }

    // 設定按鈕功能
    private fun setButton() {
        btAdd.setOnClickListener {
            var it = Intent(this, IncomeAddActivity::class.java)
            startActivity(it)
        }

        btBack.setOnClickListener {
            finish()
        }
    }

}