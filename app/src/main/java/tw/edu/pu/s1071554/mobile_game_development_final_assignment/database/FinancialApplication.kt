package tw.edu.pu.s1071554.mobile_game_development_final_assignment.database

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FinancialApplication  : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { FinancialDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { DataRepository(database.financialDataDao()) }
}