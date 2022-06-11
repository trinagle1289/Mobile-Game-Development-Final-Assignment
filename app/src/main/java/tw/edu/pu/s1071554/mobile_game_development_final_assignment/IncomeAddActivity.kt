package tw.edu.pu.s1071554.mobile_game_development_final_assignment

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.database.FinancialData
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.database.FinancialDataDAO
import java.text.SimpleDateFormat
import java.util.*

// 新增收入界面
class IncomeAddActivity : AppCompatActivity() {

    lateinit var fdao: FinancialDataDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_add)
        setView()

        val db = Room.databaseBuilder(
            applicationContext,
            FinancialDatabase::class.java, "financial_DB"
        ).build()
        fdao = db.financialDataDAO()
    }

    // 初始化UI變數
    private fun setView() {
        // 時間文字
        val etTime: EditText = findViewById(R.id.income_create_time_data)
        val currentDate: String = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(Date())
        etTime.setText(currentDate)

        // 輸入收入金額
        val etIncome: EditText = findViewById(R.id.income_create_income_data)

        // 輸入描述文字
        val etDescribe:EditText = findViewById(R.id.income_create_describe_data)

        // 返回按鈕
        val btBack: ImageButton = findViewById(R.id.income_create_back_btn)
        btBack.setOnClickListener {
            finish()
        }

        // 新增按鈕
        val btAdd: ImageButton = findViewById(R.id.income_create_add_btn)
        btAdd.setOnClickListener {
            fdao.insertIncomeData(
                FinancialData(
                    0,
                    etTime.text.toString(),
                    etIncome.text.toString().toInt(),
                    etDescribe.text.toString()
                )
            )
            finish()
        }
    }
}