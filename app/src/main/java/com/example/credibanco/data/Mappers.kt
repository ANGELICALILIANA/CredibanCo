package com.example.credibanco.data

import com.example.credibanco.database.TransactionEntity

class Mappers {

    fun dataToDtoAuthorization(authorizationVO: AuthorizationVO): AutorizationInDTO {
        return AutorizationInDTO(
            id = authorizationVO.id,
            commerceCode = authorizationVO.commerceCode,
            terminalCode = authorizationVO.terminalCode,
            amount = authorizationVO.amount,
            card = authorizationVO.card
        )
    }

    fun dtoToDataStatusAuthorization(authorizationOutDTO: AutorizationOutDTO): StatusAuthorizationVO {
        return StatusAuthorizationVO(
            receiptId = authorizationOutDTO.idReceipt,
            rrn = authorizationOutDTO.rrn,
            statusDescription = authorizationOutDTO.statusDescription
        )
    }

    fun dataToBusinnessAuthorization(authorizationVO: AuthorizationVO, authorizationOutDTO: AutorizationOutDTO): TransactionEntity {
        return TransactionEntity(
            transactionId = authorizationVO.id,
            receptiontId = authorizationOutDTO.idReceipt,
            rrn = authorizationOutDTO.rrn,
            status = authorizationOutDTO.statusDescription
        )
    }

    fun businnesToDataTransaction(transactionEntity: TransactionEntity): StatusAuthorizationVO {
        return StatusAuthorizationVO(
            id = transactionEntity.transactionId,
            rrn = transactionEntity.rrn,
            receiptId = transactionEntity.receptiontId,
            statusDescription = transactionEntity.status
        )
    }

    fun dataToDtoAnnulment(statusAuthorizationVO: StatusAuthorizationVO): AutorizationOutDTO {
        return AutorizationOutDTO(
            rrn = statusAuthorizationVO.rrn,
            statusDescription = statusAuthorizationVO.statusDescription,
        )
    }

}