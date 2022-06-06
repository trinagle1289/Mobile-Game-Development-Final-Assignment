package tw.edu.pu.s1071554.mobile_game_development_final_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import java.lang.Exception

// 新增收入界面
class IncomeAddActivity : AppCompatActivity() {

    // UI 變數
    lateinit var etTime: EditText // 時間文字
    lateinit var etIncome: EditText // 輸入收入金額
    lateinit var etDescribe: EditText // 輸入描述文字

    lateinit var btAdd: ImageButton // 新增按鈕
    lateinit var btBack: ImageButton // 返回按鈕

    lateinit var dbhelp: DBHelper
    lateinit var fdata: ArrayList<FinancialData>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_add)

        // 初始化參數
        etTime = findViewById(R.id.income_create_time_data)
        etIncome = findViewById(R.id.income_create_income_data)
        etDescribe = findViewById(R.id.income_create_describe_data)
        btAdd = findViewById(R.id.income_create_add_btn)
        btBack = findViewById(R.id.income_create_back_btn)

        dbhelp = DBHelper(this)
        fdata = ArrayList<FinancialData>()

        setView()
        setButton()


    }

    // 設定畫面資料
    private fun setView() {
        etTime.text.clear()
        etIncome.text.clear()
        etDescribe.text.clear()
    }

    // 設定按鈕功能
    private fun setButton() {
        btAdd.setOnClickListener {
            var data = FinancialData(
                0,
                etTime.text.toString(),
                Integer.valueOf(etIncome.text.toString()),
                etDescribe.text.toString()
            )
            try {
                dbhelp.insertIncomeData(data)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            finish()
        }

        btBack.setOnClickListener {
            finish()
        }
    }

}