package tw.edu.pu.s1071554.mobile_game_development_final_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView

// 收入列表介面
class IncomeListActivity : AppCompatActivity() {

    // UI 變數
    lateinit var tvTitle: TextView // 標題名稱
    lateinit var btAdd: ImageButton // 新增名單
    lateinit var btBack: ImageButton // 返回主頁面

    lateinit var list: TextView
    lateinit var dbhelp: DBHelper
    lateinit var fdata: ArrayList<FinancialData>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_list)

        // 初始化
        tvTitle = findViewById(R.id.income_list_title_text)
        btAdd = findViewById(R.id.income_list_create_item_btn)
        btBack = findViewById(R.id.income_list_back_gtn)
        list = findViewById(R.id.income_list_item_list)

        dbhelp = DBHelper(this)
        fdata = ArrayList<FinancialData>()
        setView()
        setButton()

    }

    // 設定畫面資料
    private fun setView() {
        list.text = ""
        fdata = dbhelp.showAllIncome()

        var tstr: String = ""
        for (d in fdata) {
            tstr += d.message + "\n"
            Log.v("CCC", "ID:\n" + d.id + "amount:\n" + d.amount + "message: " + d.message)
        }
        list.setText(tstr)

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