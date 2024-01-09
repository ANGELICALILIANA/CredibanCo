package com.example.credibanco.id

import com.example.credibanco.data.StatusAuthorizationVO

interface ICancelItem<statusAuthorizationVO : StatusAuthorizationVO> {

    fun annulmentTransaction(item: statusAuthorizationVO)

}