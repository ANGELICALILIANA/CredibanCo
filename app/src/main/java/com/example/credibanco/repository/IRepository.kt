package com.example.credibanco.repository

import com.example.credibanco.data.StatusAuthorizationVO
import com.example.credibanco.data.AuthorizationVO

interface IRepository {

    suspend fun getAuthorizationTransaction(authorizationVO: AuthorizationVO): StatusAuthorizationVO?

    suspend fun getAllTransactions(): List<StatusAuthorizationVO>

    suspend fun openDataBase()

    suspend fun annulmentTransaction(statusAuthorizationVO: StatusAuthorizationVO)

}