package tw.edu.pu.s1071554.mobile_game_development_final_assignment

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.database.FinancialApplication
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.database.FinancialData
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.mvvm.FinancialViewModel
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.mvvm.FinancialViewModelFactory
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.mvvm.RecyclerViewAdapter
import java.text.SimpleDateFormat
import java.util.*

// 收入列表介面
class IncomeListActivity : AppCompatActivity() {

    private val financialViewModel: FinancialViewModel by viewModels {
        FinancialViewModelFactory((application as FinancialApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_list)
        setView()

        val recyclerView = findViewById<RecyclerView>(R.id.income_list_recyclerview)

        // TODO: This may cause problems
        val adapter = financialViewModel.allIncomeData.value?.let { RecyclerViewAdapter(it) }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        financialViewModel.allIncomeData.observe(this) { data ->
            // Update the cached copy of the words in the adapter.
            data.let {
                // TODO: This may cause problems
                if (adapter != null) {
                    adapter.submitList(it)
                }
            }
        }
    }

    // 設定畫面資料
    private fun setView() {
        // 初始化UI變數
        // 標題名稱
        val currentDate: String = SimpleDateFormat("dd", Locale.getDefault()).format(Date())
        val tvTitle: TextView = findViewById(R.id.income_list_title_text)
        val title =  currentDate + "月 收入表"
        tvTitle.text = title

        // 新增名單
        val btAdd: ImageButton = findViewById(R.id.income_list_create_item_btn)
        btAdd.setOnClickListener {
            // Caller
            val it = Intent(this, IncomeAddActivity::class.java)
            getResult.launch(it)
        }

        // 返回主頁面
        val btBack: ImageButton = findViewById(R.id.income_list_back_gtn)
        btBack.setOnClickListener {
            finish()
        }
    }

    // Receiver
    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK){
                val time = it.data?.getStringExtra(IncomeAddActivity.EXTRA_TIME)
                val amount = it.data?.getStringExtra(IncomeAddActivity.EXTRA_AMOUNT)
                val description = it.data?.getStringExtra(IncomeAddActivity.EXTRA_DESCRIPTION)
                Toast.makeText(this, time + " " + amount + " " + description, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "未儲存", Toast.LENGTH_SHORT).show()
            }
        }
}