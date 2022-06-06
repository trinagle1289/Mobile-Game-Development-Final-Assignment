package tw.edu.pu.s1071554.mobile_game_development_final_assignment

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context?
) : SQLiteOpenHelper(context, "fin_db", null, 1) {
    // fin_db 為 期末的資料庫 名稱
    // income_model 為 資料庫 存放 收入財務資料 的表格
    // expense_model 為 資料庫 存放 支出財務資料 的表格

    var incomeTable: String = "income_model" // 收入資料表
    var expenseTable: String = "expense_model" // 支出資料表

    // 建立資料表
    // CREATE TABLE IF NOT EXISTS fd_model  表示如果不存在 fd_model 則建立資料表
    // id INTEGER PRIMARY KEY AUTOINCREMENT 表示設定 id 為 整數 並且數值則會自動增量
    // time TEXT                            表示設定 time 為 文字格式(TEXT)
    // amount INTEGER                       表示設定 amount 為 整數
    // message TEXT                         表示設定 message 為 文字格式(TEXT)
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(
            "CREATE TABLE IF NOT EXISTS $incomeTable (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "time TEXT," +
                    "amount TEXT," +
                    "message TEXT)"
        )

        p0?.execSQL(
            "CREATE TABLE IF NOT EXISTS $expenseTable (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "time TEXT," +
                    "amount TEXT," +
                    "message TEXT)"
        )

    }

    // 每次更新資料表版本時，丟棄舊有的表格
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $incomeTable")
        p0?.execSQL("DROP TABLE IF EXISTS $expenseTable")
        onCreate(p0)
    }

    // 取得所有收入資料
    fun showAllIncome(): ArrayList<FinancialData> {
        return showAll(incomeTable)
    }

    // 取得所有支出資料
    fun showAllExpense(): ArrayList<FinancialData> {
        return showAll(expenseTable)
    }

    // 導入收入資料
    fun insertIncomeData(finData: FinancialData) {
        insertData(incomeTable, finData)
    }

    // 導入支出資料
    fun expenseIncomeData(finData: FinancialData) {
        insertData(expenseTable, finData)
    }

    // dbName 表示要儲存的資料表名稱
    private fun showAll(dbName: String): ArrayList<FinancialData> {

        var db = writableDatabase
        var finData = ArrayList<FinancialData>() // 財務資料
        var c1 = db.rawQuery("SELECT * FROM $dbName", null) // 資料表
        // 抓每行的資料表到 finData 變數
        while (c1.moveToNext()) {
            var idCol = c1.getColumnIndex("id")
            var id = c1.getInt(idCol)

            var timeCol = c1.getColumnIndex("time")
            var time = c1.getString(timeCol)

            var amountCol = c1.getColumnIndex("amount")
            var amount = c1.getString(amountCol)

            var messageCol = c1.getColumnIndex("message")
            var message = c1.getString(messageCol)

            var item = FinancialData(id, time, amount, message)
            finData.add(item)
        }

        return finData
    }

    // 刪除資料表資料
    private fun delData(dbName: String, id: Int) {
        var db = writableDatabase
        var selstr = "id=$id"
        db.delete(dbName, selstr, arrayOf())
    }

    // 新增資料表資料
    private fun insertData(dbName: String, finData: FinancialData) {

        var db = writableDatabase

        var sqlstr: String =
            "INSERT INTO ${dbName}(time, amount, message) VALUES ( ${finData.time}, ${finData.amount}, ${finData.message})"

        db.execSQL(sqlstr)
    }

}