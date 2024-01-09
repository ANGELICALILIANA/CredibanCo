package com.example.credibanco.data

data class StatusAuthorizationVO(
    var id: String = "",
    val receiptId: String = "",
    val rrn: String = "",
    var statusDescription: String = ""
)