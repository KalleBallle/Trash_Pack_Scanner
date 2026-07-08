package com.example.packscan.UI.CollectionUi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.packscan.Data.Figure

@Composable
fun TitleTTP(
    text : String,
    backgroundColor : Color
)
{
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 8.dp, end = 12.dp, bottom = 0.dp, top = 34.dp)
            .background(color = backgroundColor)
    ) {
        Box {
            Text(
                text = text,
                style = TextStyle(
                    color = Color.Black,
                    drawStyle = Stroke(width = 15.0f),
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 50.sp
            )

            Text(
                text = text,
                fontSize = 50.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black,

                        offset = Offset(12f, 12f),
                        blurRadius = 16f
                    )

                ),
                color = Color("#FFC107".toColorInt())
            )


        }

    }
}
@Composable
fun TitleTTP(
    text: String,
    backgroundColor: Color,
    subFigure: List<Figure>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 12.dp, bottom = 0.dp, top = 34.dp)
            .background(color = backgroundColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Main background box with your hardcoded 100.dp height
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            contentAlignment = Alignment.Center // Centers the inner layouts vertically
        ) {

            // Title Safe-Zone Wrapper
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 70.dp),
                contentAlignment = Alignment.Center // Centers the AutoText layers vertically inside the 100.dp height
            ) {
                AutoText(
                    text = text,
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 50.sp,
                        textAlign = TextAlign.Center,
                        drawStyle = Stroke(width = 15.0f)
                    )
                )

                AutoText(
                    text = text,
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 50.sp,
                        textAlign = TextAlign.Center,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(12f, 12f),
                            blurRadius = 16f
                        )
                    ),
                    color = Color("#FFC107".toColorInt())
                )
            }

            // Stats pinned to the middle-right side of the 100.dp row
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd) // Aligns perfectly with the horizontal line of the text
                    .padding(end = 8.dp)
            ) {
                GroupStats(subFigure)
            }
        }
    }
}