package com.example.gamestore.model

import java.io.Serializable

data class StoreItem(
    val id: Int,
    val displayName: String,
    val shortDescription: String,
    val longDescription: String,
    val priceInEur: Double,
    val imageResId: Int
) : Serializable
