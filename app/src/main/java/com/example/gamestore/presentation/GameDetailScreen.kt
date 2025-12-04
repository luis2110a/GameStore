package com.example.gamestore.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.gamestore.model.GameInfo
import com.example.gamestore.model.StoreItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(
    game: GameInfo,
    onBack: () -> Unit,
    onPurchase: (StoreItem) -> Unit
) {
    var selectedItem by remember { mutableStateOf<StoreItem?>(null) }
    var isSheetOpen by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = game.title, fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            GameHeaderCard(game = game)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = when (game.title) {
                    "NBA 2K26" -> "2k26 Store"
                    "Clash Royale" -> "Royale Items"
                    else -> "UltraStore"
                },
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(game.itemsForSale) { item ->
                    StoreItemRow(
                        item = item,
                        onClick = {
                            selectedItem = item
                            isSheetOpen = true
                            scope.launch { sheetState.show() }
                        }
                    )
                }
            }
        }
    }

    if (isSheetOpen && selectedItem != null) {
        ModalBottomSheet(
            onDismissRequest = {
                isSheetOpen = false
                selectedItem = null
            },
            sheetState = sheetState
        ) {
            PurchaseSheetContent(
                item = selectedItem!!,
                onConfirm = {
                    onPurchase(selectedItem!!)
                    isSheetOpen = false
                    selectedItem = null
                }
            )
        }
    }
}

@Composable
private fun GameHeaderCard(game: GameInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = game.logoResId),
                contentDescription = game.title,
                modifier = Modifier
                    .height(100.dp)
                    .weight(0.8f),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier.weight(1.2f)
            ) {
                Text(
                    text = game.summary,
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun StoreItemRow(
    item: StoreItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.displayName,
                modifier = Modifier
                    .height(80.dp)
                    .weight(0.8f),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier.weight(1.5f)
            ) {
                Text(
                    text = item.displayName,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = item.shortDescription,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Box(
                modifier = Modifier.weight(0.7f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "%.2f€".format(item.priceInEur),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun PurchaseSheetContent(
    item: StoreItem,
    onConfirm: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = item.imageResId),
            contentDescription = item.displayName,
            modifier = Modifier.height(120.dp),
            contentScale = ContentScale.Fit
        )
        Text(text = item.displayName, fontWeight = FontWeight.Bold)
        Text(text = item.longDescription)
        Text(text = "Preço: %.2f€".format(item.priceInEur), fontWeight = FontWeight.SemiBold)

        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()

        val baseColor = Color(0xFF4C8DFF)
        val pressedColor = Color(0xFF7FB3FF)

        val buttonColor by animateColorAsState(
            targetValue = if (isPressed) pressedColor else baseColor,
            label = "buttonColor"
        )

        val buttonScale by animateFloatAsState(
            targetValue = if (isPressed) 1.05f else 1f,
            label = "buttonScale"
        )

        Button(
            onClick = onConfirm,
            modifier = Modifier
                .fillMaxWidth()
                .scale(buttonScale),
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                contentColor = Color.White
            )
        ) {
            Text(text = "Compra Agora")
        }
    }
}
