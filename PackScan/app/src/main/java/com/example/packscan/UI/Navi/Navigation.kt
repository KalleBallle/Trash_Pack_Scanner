package com.example.packscan.UI.Navi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import androidx.compose.foundation.background
import androidx.compose.foundation.border

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.packscan.R
import com.example.packscan.UI.collectionScreen
import com.example.packscan.UI.startScreen

@Composable
fun Navigation()
{
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.camScreen, builder = {
        composable(route = Routes.camScreen) { startScreen(navController) }
        composable(route = Routes.collectionScreen) { collectionScreen(navController) }
    })
}

@Composable
fun NavigationUI(navController: NavController)
{
    Box(
        modifier = Modifier.fillMaxWidth()
    )
    {
    Row(
        modifier = Modifier.fillMaxWidth()
            // .padding(bottom = 32.dp, start = 16.dp, top = 0.dp, end = 16.dp)
        .align(Alignment.BottomCenter)
        , horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Button(
            modifier = Modifier.weight(1f).border(shape = RectangleShape,
                width = 5.dp, color = Color("#3A3A3C".toColorInt())),
            onClick = {
                navController.navigate(route = Routes.camScreen)
            }
            ,shape = RoundedCornerShape(0.dp)
            ,colors = ButtonDefaults.buttonColors(
                containerColor = Color("#1C1C1E".toColorInt())
            )

            ) {
            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.outline_add_a_photo_24)
                , contentDescription = "camera picture"
                , tint = Color("#2D6A4F".toColorInt())
            , modifier = Modifier.size(50.dp))

        }
        Spacer(
            modifier = Modifier.width(16.dp)
                .height(50.dp)
            .background(color = Color("#3A3A3C".toColorInt()))
        )
        Button(
            modifier = Modifier.background(Color.LightGray)
                .weight(1f)
                .border(shape = RectangleShape, width = 5.dp,
                    color = Color("#3A3A3C".toColorInt())
                )
            , onClick = {navController.navigate(route = Routes.collectionScreen)}
            ,shape = RoundedCornerShape(0.dp)
            ,colors = ButtonDefaults.buttonColors(
                containerColor = Color("#1C1C1E".toColorInt()))
        ) {
            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.outline_book_5_24)
                , contentDescription = "book picture"
            , tint = Color("#2D6A4F".toColorInt())
                , modifier = Modifier.size(50.dp))

        }

    }
}
}