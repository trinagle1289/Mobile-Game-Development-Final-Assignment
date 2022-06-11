package tw.edu.pu.s1071554.mobile_game_development_final_assignment.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "financial_data")
data class FinancialData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "UID") val uid: Int = 0,  // 資料 ID
    @ColumnInfo(name = "TIME_STAMP")  val time: String?,  // 時間
    @ColumnInfo(name = "AMOUNT")  val amount: Int, // 金額
    @ColumnInfo(name = "MESSAGE") val message: String? // 描述訊息
)
