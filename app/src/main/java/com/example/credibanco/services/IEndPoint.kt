package com.example.credibanco.services

import com.example.credibanco.data.AnnulmentDTO
import com.example.credibanco.data.AutorizationInDTO
import com.example.credibanco.data.AutorizationOutDTO
import com.example.credibanco.data.Constant.ENDPOINT_ANNULMENT
import com.example.credibanco.data.Constant.ENDPOINT_AUTHORIZATION
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface IEndPoint {

    @POST(ENDPOINT_AUTHORIZATION)
    suspend fun getAuthorization(
        @Header("Authorization") authorizationHeader: String,
        @Body authorization: AutorizationInDTO
    ): AutorizationOutDTO

    @POST(ENDPOINT_ANNULMENT)
    suspend fun annulmentTransaction(
        @Header("Authorization") authorizationHeader: String,
        @Body autorizationOutDTO: AutorizationOutDTO
    ): AnnulmentDTO

}