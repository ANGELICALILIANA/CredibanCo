package com.example.credibanco.repository

import com.example.credibanco.data.AutorizationOutDTO
import com.example.credibanco.database.DBEntity
import com.example.credibanco.services.IEndPoint
import com.example.credibanco.data.Mappers
import com.example.credibanco.data.StatusAuthorizationVO
import com.example.credibanco.database.TransactionDataBase
import android.content.Context
import android.util.Log
import com.example.credibanco.data.AuthorizationVO
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Base64
import javax.inject.Inject

class Repository @Inject constructor(
    @ApplicationContext val context: Context,
    private val api: IEndPoint,
    private val db: TransactionDataBase
) : IRepository {

    /**
     * Servicio que autoriza transacciones
     */
    override suspend fun getAuthorizationTransaction(authorizationVO: AuthorizationVO): StatusAuthorizationVO? {
        val authorization = Mappers().dataToDtoAuthorization(authorizationVO)
        return try {
            val responseAuthorization = api.getAuthorization(
                "Basic " + Base64.getEncoder().encodeToString("000123000ABC".encodeToByteArray()),
                authorization
            )
            saveTransaction(authorizationVO, responseAuthorization)
            Mappers().dtoToDataStatusAuthorization(responseAuthorization)
        } catch (e: Exception) {
            Log.e("ERROR", "$e")
            null
        }

    }

    /**
     * Guardado de transacciones en base de datos
     */
    private fun saveTransaction(authorizationVO: AuthorizationVO, responseAuthorization: AutorizationOutDTO) {
        db.getTransactionDao().insertOrUpdateRow(
            Mappers().dataToBusinnessAuthorization(authorizationVO, responseAuthorization))
    }

    /**
     * Obtiene todas las transacciones de base de datos
     */
    override suspend fun getAllTransactions(): List<StatusAuthorizationVO>{
        val allTransactions = db.getTransactionDao().getTransactions()
        return allTransactions.map { Mappers().businnesToDataTransaction(it) }
    }

    /**
     * Realiza una inserción inicial a la base de datos
     */
    override suspend fun openDataBase(){
        db.getDBDao().insertOrUpdate(DBEntity())
    }

    /**
     * Servicio que anula transacciones
     */
    override suspend fun annulmentTransaction(statusAuthorizationVO: StatusAuthorizationVO) {
        val status = Mappers().dataToDtoAnnulment(statusAuthorizationVO)
        val responseAnnulment = api.annulmentTransaction("Basic " + Base64.getEncoder().encodeToString("000123000ABC".encodeToByteArray()), status)
        if (responseAnnulment.statusDescription == "Aprobada"){
            db.getTransactionDao().updateStatus(statusAuthorizationVO.receiptId, "Anulada")
        }
    }

}