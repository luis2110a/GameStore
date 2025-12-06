package com.example.gamestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.gamestore.data.GameCatalog
import com.example.gamestore.presentation.GameListScreen
import com.example.gamestore.presentation.HistoryScreen
import com.example.gamestore.presentation.ProfileScreen
import com.example.gamestore.ui.theme.AppBottomBar
import com.example.gamestore.ui.theme.GameStoreTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val games = GameCatalog.allGames

        setContent {
            GameStoreTheme {

                var selectedTab by remember { mutableStateOf(0) }

                Scaffold(
                    bottomBar = {
                        AppBottomBar(
                            selectedTab = selectedTab,
                            onTabSelected = { selectedTab = it }
                        )
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        when (selectedTab) {
                            0 -> GameListScreen(
                                games = games,
                                onGameSelected = { game ->
                                    val intent =
                                        GameDetailActivity.newIntent(this@MainActivity, game)
                                    startActivity(intent)
                                }
                            )

                            1 -> HistoryScreen()

                            2 -> ProfileScreen()
                        }
                    }
                }
            }
        }
    }
}
