package com.example.gamestore.data

import com.example.gamestore.R
import com.example.gamestore.model.GameInfo
import com.example.gamestore.model.StoreItem

object GameCatalog {

    val allGames: List<GameInfo> = listOf(

        GameInfo(
            gameId = 1,
            title = "NBA 2K26",
            summary = "A oportunidade de mostrares que o rei és tu está em jogo no MyCAREER, MyTEAM, MyNBA, The W e Play Now. Mostra os teus moves com hiperrealismo.",
            bannerResId = R.drawable.nba2k26_banner,
            logoResId = R.drawable.nba2k26_logo,
            itemsForSale = listOf(
                StoreItem(
                    id = 101,
                    displayName = "VC Pack 450k",
                    shortDescription = "450.000 VC para MyCareer",
                    longDescription = "Pacote de 450.000 VC para evoluir o teu jogador MyCareer, comprar animações, roupas e boosts.",
                    priceInEur = 89.79,
                    imageResId = R.drawable.vc_pack
                ),
                StoreItem(
                    id = 102,
                    displayName = "All-Star Jerseys",
                    shortDescription = "Equipamentos das maiores estrelas",
                    longDescription = "Coleção de camisolas inspiradas em lendas da NBA, destinadas a grandeza.",
                    priceInEur = 9.99,
                    imageResId = R.drawable.jerseys_pack
                ),
                StoreItem(
                    id = 103,
                    displayName = "Skill Boost Bundle",
                    shortDescription = "Boosts temporários de atributos",
                    longDescription = "Conjunto de boosts para lançamento, defesa e velocidade em jogos MyCareer durante vários encontros.",
                    priceInEur = 4.99,
                    imageResId = R.drawable.skill_boosts
                )
            )
        ),


        GameInfo(
            gameId = 2,
            title = "Clash Royale",
            summary = "Batalhas 1v1 em tempo real com cartas, feitiços e tropas épicas. Dois reis entram, apenas um sai.",
            bannerResId = R.drawable.clash_royale_banner,
            logoResId = R.drawable.clash_royale_logo,
            itemsForSale = listOf(
                StoreItem(
                    id = 201,
                    displayName = "Gem Pack XL",
                    shortDescription = "Montanha de gemas",
                    longDescription = "Pacote de gemas para abrires baús raros, acelerares melhorias e comprares ofertas especiais na loja.",
                    priceInEur = 119.99,
                    imageResId = R.drawable.gems_xl
                ),
                StoreItem(
                    id = 202,
                    displayName = "Gold River",
                    shortDescription = "Rio de ouro",
                    longDescription = "Um rio de ouro para evoluíres cartas e experimentares novos decks rapidamente.",
                    priceInEur = 35.99,
                    imageResId = R.drawable.gold_crate
                ),
                StoreItem(
                    id = 203,
                    displayName = "Legendary Chest",
                    shortDescription = "Baú lendário",
                    longDescription = "Baú lendário com garantia de carta lendária.",
                    priceInEur = 9.99,
                    imageResId = R.drawable.magic_chest
                )
            )
        )
    )
}
