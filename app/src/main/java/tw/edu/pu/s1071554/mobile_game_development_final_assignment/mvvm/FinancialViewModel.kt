package tw.edu.pu.s1071554.mobile_game_development_final_assignment.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.database.DataRepository
import tw.edu.pu.s1071554.mobile_game_development_final_assignment.database.FinancialData

class FinancialViewModel(private val repository: DataRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allIncomeData: LiveData<List<FinancialData>> = repository.allIncomeData.asLiveData()
    val allExpenseData: LiveData<List<FinancialData>> = repository.allExpenseData.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(financialData: FinancialData) = viewModelScope.launch {
        repository.insert(financialData)
    }

    fun delete(financialData: FinancialData) = viewModelScope.launch {
        repository.delete(financialData)
    }
}

class FinancialViewModelFactory(private val repository: DataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FinancialViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FinancialViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}