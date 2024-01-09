package com.example.credibanco.data

import com.google.gson.annotations.SerializedName

data class AutorizationOutDTO(
    @SerializedName("receiptId")
    val idReceipt: String = "",
    @SerializedName("rrn")
    val rrn: String = "",
    @SerializedName("statusDescription")
    val statusDescription: String = ""
)

data class AutorizationInDTO(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("commerceCode")
    val commerceCode: String = "",
    @SerializedName("terminalCode")
    val terminalCode: String = "",
    @SerializedName("amount")
    val amount: String = "",
    @SerializedName("card")
    val card: String = ""
)

data class AnnulmentDTO(
    @SerializedName("statusDescription")
    val statusDescription: String = ""
)
