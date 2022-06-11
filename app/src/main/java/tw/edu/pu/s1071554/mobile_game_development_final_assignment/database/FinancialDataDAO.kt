package tw.edu.pu.s1071554.mobile_game_development_final_assignment.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.database.FinancialData

@Dao
interface FinancialDataDAO {
    // 取得收入資料
    @Query("SELECT * FROM financial_data WHERE AMOUNT > 0")
    fun loadAllIncomeData(): Flow<List<FinancialData>>

    // 取得支出資料
    @Query("SELECT * FROM financial_data WHERE AMOUNT < 0")
    fun loadAllExpenseData(): Flow<List<FinancialData>>

    // 導入資料
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(data: FinancialData)

    // 刪除資料
    @Delete
    fun delete(data: FinancialData)

    @Query("DELETE FROM financial_data")
    suspend fun deleteAll()
}