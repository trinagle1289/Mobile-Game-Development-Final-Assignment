package tw.edu.pu.s1071554.mobile_game_development_final_assignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [FinancialData::class], version = 1, exportSchema = false)
abstract class FinancialDatabase : RoomDatabase() {

    abstract fun financialDataDao(): FinancialDataDAO

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var financialDataDao = database.financialDataDao()

                    // Delete all content here.
                    financialDataDao.deleteAll()

                    // Add sample words.
                    var d1 = FinancialData(0,"2022/06/10",100,"Hello")
                    financialDataDao.insertData(d1)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: FinancialDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): FinancialDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FinancialDatabase::class.java,
                    "financial_db"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}