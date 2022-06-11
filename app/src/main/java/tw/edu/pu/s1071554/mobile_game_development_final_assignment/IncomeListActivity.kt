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
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.mvvm.OnItemClickListener
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.mvvm.RecyclerViewAdapter
import java.text.SimpleDateFormat
import java.util.*

// 收入列表介面
class IncomeListActivity : AppCompatActivity(), OnItemClickListener {

    private val financialViewModel: FinancialViewModel by viewModels {
        FinancialViewModelFactory((application as FinancialApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_list)
        setView()

        val recyclerView = findViewById<RecyclerView>(R.id.income_list_recyclerview)

        // TODO: This may cause problems
        val adapter = RecyclerViewAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        financialViewModel.allIncomeData.observe(this) { data ->
            // Update the cached copy of the words in the adapter.
            data.let { adapter.submitList(it) }
        }
    }

    // 設定畫面資料
    private fun setView() {
        // 初始化UI變數
        // 標題名稱
        val currentDate: String = SimpleDateFormat("MM", Locale.getDefault()).format(Date())
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
        val btBack: ImageButton = findViewById(R.id.income_list_back_btn)
        btBack.setOnClickListener {
            finish()
        }
    }

    // Receiver
    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK){
                Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
                var uid = 0;
                if (it.data?.hasExtra(IncomeAddActivity.EXTRA_UID) == true) {
                    uid = it.data?.getIntExtra(IncomeAddActivity.EXTRA_UID,0)!!
                }
                val time = it.data?.getStringExtra(IncomeAddActivity.EXTRA_TIME)
                val amount = it.data?.getIntExtra(IncomeAddActivity.EXTRA_AMOUNT,0)!!
                val description = it.data?.getStringExtra(IncomeAddActivity.EXTRA_DESCRIPTION)
                val data = FinancialData(uid, time, amount, description)
                Toast.makeText(this, uid.toString() + " " + time + " " + amount + " " + description, Toast.LENGTH_SHORT).show()
                if (it.data?.hasExtra(IncomeAddActivity.EXTRA_UPDATE) == true) {
                    financialViewModel.update(data)
                    Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show()
                } else {
                    financialViewModel.insert(data)
                    Toast.makeText(this, "儲存成功", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "未儲存", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onItemClick(financialData: FinancialData?) {
        val it = Intent(this, IncomeAddActivity::class.java)
        if (financialData != null) {
            it.putExtra(IncomeAddActivity.EXTRA_UPDATE, IncomeAddActivity.EXTRA_UPDATE)
            it.putExtra(IncomeAddActivity.EXTRA_UID, financialData.uid)
            it.putExtra(IncomeAddActivity.EXTRA_TIME, financialData.time)
            it.putExtra(IncomeAddActivity.EXTRA_AMOUNT, financialData.amount)
            it.putExtra(IncomeAddActivity.EXTRA_DESCRIPTION, financialData.message)
        }
        getResult.launch(it)
    }
}
