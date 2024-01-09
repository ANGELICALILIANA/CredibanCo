package com.example.credibanco.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.credibanco.repository.IRepository
import com.example.credibanco.data.StatusAuthorizationVO
import com.example.credibanco.data.AuthorizationVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: IRepository
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.Main)

    private val transactions = MutableLiveData<List<StatusAuthorizationVO>>()
    fun getAllTransactions(): LiveData<List<StatusAuthorizationVO>> = transactions

    fun getAuthorizationProcessTransaction(authorizationVO: AuthorizationVO){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAuthorizationTransaction(authorizationVO)
        }
    }

    fun getTransactionStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            transactions.postValue(repository.getAllTransactions())
        }
    }

    fun createDataBase(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.openDataBase()
        }
    }

    fun cancelTransaction(statusAuthorizationVO: StatusAuthorizationVO) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.annulmentTransaction(statusAuthorizationVO)
        }
    }

}