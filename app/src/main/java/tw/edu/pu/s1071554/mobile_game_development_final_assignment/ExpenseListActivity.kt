package tw.edu.pu.s1071554.mobile_game_development_final_assignment

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
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

// 支出列表介面
class ExpenseListActivity : AppCompatActivity(), OnItemClickListener {

    private val financialViewModel: FinancialViewModel by viewModels {
        FinancialViewModelFactory((application as FinancialApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_list)
        setView()

        val recyclerView = findViewById<RecyclerView>(R.id.expense_list_recyclerview)

        // TODO: This may cause problems
        val adapter = RecyclerViewAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        financialViewModel.allExpenseData.observe(this) { data ->
            // Update the cached copy of the words in the adapter.
            data.let { adapter.submitList(it) }
        }
    }

    // 設定畫面資料
    private fun setView() {
        // 初始化UI變數
        // 標題名稱
        val currentDate: String = SimpleDateFormat("MM", Locale.getDefault()).format(Date())
        val tvTitle: TextView = findViewById(R.id.expense_list_title_text)
        val title =  currentDate + "月 支出表"
        tvTitle.text = title

        // 新增名單
        val btAdd: ImageButton = findViewById(R.id.expense_list_add_item_btn)
        btAdd.setOnClickListener {
            // Caller
            val it = Intent(this, ExpenseAddActivity::class.java)
            getResult.launch(it)
        }

        // 返回主頁面
        val btBack: ImageButton = findViewById(R.id.expense_list_back_btn)
        btBack.setOnClickListener {
            finish()
        }
    }

    // Receiver
    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK){
                val uid = it.data?.getIntExtra(ExpenseAddActivity.EXTRA_UID,0)!!
                val time = it.data?.getStringExtra(ExpenseAddActivity.EXTRA_TIME)
                val description = it.data?.getStringExtra(ExpenseAddActivity.EXTRA_DESCRIPTION)

                if (it.data?.hasExtra(ExpenseAddActivity.EXTRA_UPDATE) == true) {
                    val amount = it.data?.getIntExtra(ExpenseAddActivity.EXTRA_AMOUNT,0)!!
                    val data = FinancialData(uid, time, amount, description)
                    financialViewModel.update(data)
                    Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show()
                } else {
                    val amount = -1 * (it.data?.getIntExtra(ExpenseAddActivity.EXTRA_AMOUNT,0)!!)
                    val data = FinancialData(uid, time, amount, description)
                    financialViewModel.insert(data)
                    Toast.makeText(this, "儲存成功", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "未儲存", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onItemClick(financialData: FinancialData?) {
        val it = Intent(this, ExpenseAddActivity::class.java)
        if (financialData != null) {
            it.putExtra(ExpenseAddActivity.EXTRA_UPDATE, ExpenseAddActivity.EXTRA_UPDATE)
            it.putExtra(ExpenseAddActivity.EXTRA_UID, financialData.uid)
            it.putExtra(ExpenseAddActivity.EXTRA_TIME, financialData.time)
            it.putExtra(ExpenseAddActivity.EXTRA_AMOUNT, financialData.amount)
            it.putExtra(ExpenseAddActivity.EXTRA_DESCRIPTION, financialData.message)
        }
        getResult.launch(it)
    }
}

