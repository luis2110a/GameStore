package com.example.gamestore.model

import java.io.Serializable

data class GameInfo(
    val gameId: Int,
    val title: String,
    val summary: String,
    val bannerResId: Int,
    val logoResId: Int,
    val itemsForSale: List<StoreItem>
) : Serializable
