package tw.edu.pu.s1071554.mobile_game_development_final_assignment

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageButton
import java.text.SimpleDateFormat
import java.util.*

// 新增支出界面
class ExpenseAddActivity : AppCompatActivity() {
    private var uid = 0
    private var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_add)

        // 時間文字
        val etTime: EditText = findViewById(R.id.expense_create_time_data)

        // 輸入收入金額
        val etExpense: EditText = findViewById(R.id.expense_create_expense_data)
        // 輸入描述文字
        val etDescribe:EditText = findViewById(R.id.expense_create_describe_data)

        // 返回按鈕
        val btBack: ImageButton = findViewById(R.id.expense_create_back_btn)
        btBack.setOnClickListener {
            finish()
        }

        // 新增按鈕
        val replyIntent = Intent()
        val btAdd: ImageButton = findViewById(R.id.expense_create_add_btn)
        btAdd.setOnClickListener {
            if(TextUtils.isEmpty(etExpense.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val time = etTime.text.toString()
                val amount = etExpense.text.toString().toInt() * -1
                val description = etDescribe.text.toString()
                if (edit)
                    replyIntent.putExtra(EXTRA_UPDATE, EXTRA_UPDATE)
                replyIntent.putExtra(EXTRA_UID, uid)
                replyIntent.putExtra(EXTRA_TIME, time)
                replyIntent.putExtra(EXTRA_AMOUNT, amount)
                replyIntent.putExtra(EXTRA_DESCRIPTION, description)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

        // get data comes from main activity.
        if (intent.hasExtra(EXTRA_UPDATE)) {
            edit = true
        }

        val intent = intent
        if (intent.hasExtra(EXTRA_UID)) {
            uid = intent.getIntExtra(EXTRA_UID, 0)
        }

        if (intent.hasExtra(EXTRA_TIME)) {
            val txt = intent.getStringExtra(EXTRA_TIME).toString()
            etTime.setText(txt)
        } else {
            val currentDate: String =
                SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(Date())
            etTime.setText(currentDate)
        }

        if (intent.hasExtra(EXTRA_AMOUNT)) {
            val amount = intent.getIntExtra(EXTRA_AMOUNT, 0)
            if (amount != 0)
                etExpense.setText(amount.toString())
        }

        if (intent.hasExtra(EXTRA_DESCRIPTION)) {
            val msg = intent.getStringExtra(EXTRA_DESCRIPTION).toString()
            etDescribe.setText(msg)
        }
    }

    companion object {
        const val EXTRA_UPDATE = "tw.edu.pu.s1071554.mobile_game_development_final_assignment.UPDATE";
        const val EXTRA_UID = "tw.edu.pu.s1071554.mobile_game_development_final_assignment.UID";
        const val EXTRA_TIME = "tw.edu.pu.s1071554.mobile_game_development_final_assignment.TIME";
        const val EXTRA_AMOUNT = "tw.edu.pu.s1071554.mobile_game_development_final_assignment.AMOUNT";
        const val EXTRA_DESCRIPTION = "tw.edu.pu.s1071554.mobile_game_development_final_assignment.DESCRIPTION";
    }
}