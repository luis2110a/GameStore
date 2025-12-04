package com.example.gamestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.gamestore.data.GameCatalog
import com.example.gamestore.presentation.GameListScreen
import com.example.gamestore.ui.theme.GameStoreTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val games = GameCatalog.allGames

        setContent {
            GameStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameListScreen(
                        games = games,
                        onGameSelected = { game ->
                            val intent = GameDetailActivity.newIntent(this, game)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}
