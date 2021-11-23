package com.example.test_valery.data.Resp

import java.io.Serializable

data class Status(
    val sentCount: Int,
    val verified: Boolean
):Serializable