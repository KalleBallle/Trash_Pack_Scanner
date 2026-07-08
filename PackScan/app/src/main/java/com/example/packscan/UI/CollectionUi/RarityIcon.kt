package com.example.packscan.UI.CollectionUi

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import com.example.packscan.Data.Figure
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.packscan.R

@Composable
fun RarityIcon (
    figure: Figure
)
{
    var color : Color = Color.Red
    when ( figure.rarity) {
        "Common" -> color = Color.Green
        "Rare" -> color = Color.Yellow
        "Ultra Rare" -> color = Color.Blue
        "Special Edition" -> color = Color("#800080".toColorInt())


    }
    Icon(
        tint = color
        , contentDescription = "rarity trashcan"
        , imageVector = ImageVector.vectorResource(id = R.drawable.trash)
        , modifier = Modifier.size(20.dp)
    )

}
@Composable
fun RarityIcon (
    rarity : String
)
{
    var color : Color = Color.Red
    when ( rarity) {
        "Common" -> color = Color.Green
        "Rare" -> color = Color.Yellow
        "Ultra Rare" -> color = Color.Blue
        "Special Edition" -> color = Color("#800080".toColorInt())


    }
    Icon(
        tint = color
        , contentDescription = "rarity trashcan"
        , imageVector = ImageVector.vectorResource(id = R.drawable.trash)
        , modifier = Modifier.size(20.dp)
    )

}
