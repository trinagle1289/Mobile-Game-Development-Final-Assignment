package tw.edu.pu.s1071554.mobile_game_development_final_assignment.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class DataRepository(private val financialDataDAO: FinancialDataDAO) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allIncomeData: Flow<List<FinancialData>> = financialDataDAO.loadAllIncomeData()
    val allExpenseData: Flow<List<FinancialData>> = financialDataDAO.loadAllExpenseData()


    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(financialData: FinancialData) {
        financialDataDAO.insertData(financialData)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(financialData: FinancialData) {
        financialDataDAO.delete(financialData)
    }
}