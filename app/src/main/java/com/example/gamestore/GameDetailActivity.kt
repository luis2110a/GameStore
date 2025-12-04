package com.example.gamestore

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.gamestore.model.GameInfo
import com.example.gamestore.model.StoreItem
import com.example.gamestore.presentation.GameDetailScreen
import com.example.gamestore.ui.theme.GameStoreTheme

class GameDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val game = intent.getSerializableExtra(EXTRA_GAME) as? GameInfo
        if (game == null) {
            finish()
            return
        }

        setContent {
            GameStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameDetailScreen(
                        game = game,
                        onBack = { finish() },
                        onPurchase = { item -> handlePurchase(item) }
                    )
                }
            }
        }
    }

    private fun handlePurchase(item: StoreItem) {
        val message = "Acabaste de comprar ${item.displayName} por %.2f€".format(item.priceInEur)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val EXTRA_GAME = "extra_game"

        fun newIntent(context: Context, game: GameInfo): Intent {
            return Intent(context, GameDetailActivity::class.java).apply {
                putExtra(EXTRA_GAME, game)
            }
        }
    }
}
