package com.example.packscan.UI.CollectionUi

import android.graphics.drawable.Icon
import androidx.compose.runtime.Composable
import com.example.packscan.Data.Figure
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.core.graphics.toColorInt

import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.packscan.UI.CollectionUi.AutoText
import com.example.packscan.UI.Navi.NavigationUI
import com.example.packscan.R
import com.example.packscan.UI.CollectionUi.RarityIcon


@Composable
fun FigureCard (
    figure: Figure
) {
    var opacity = 0.5f
    if (figure.collected) {

        opacity = 1f
    }
    Column()
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        )
        {
            if (figure.collected) {
                Text(
                    text = 'X' + figure.quantity.toString(),
                    fontSize = 20.sp,
                    color = Color("#FFEB3B".toColorInt())
                )
            }
        }
        Card(
            modifier = Modifier.alpha(opacity).fillMaxWidth(), colors = CardDefaults.cardColors(
                containerColor = Color.White
            ), border = BorderStroke(5.dp, Color.Black)
        )
        {
            Box(Modifier.fillMaxWidth())
            {
                if (figure.team == "LIMITED EDITION") {
                    Image(
                        painter = painterResource(id = R.drawable.gold),
                        contentDescription = null,
                        modifier = Modifier.matchParentSize(),
                        contentScale = ContentScale.Crop
                    )

                }
                Column() {


                    Row(
                        Modifier.fillMaxWidth().padding(8.dp, 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        RarityIcon(figure)
                        /*
                androidx.compose.material3.Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "trash can",
                    modifier = Modifier.size(30.dp)
                )

                 */

                        Text(
                            text = figure.id, fontSize = 16.sp
                        )


                    }
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        if (figure.imagePath != null) {
                            Image(
                                painter = rememberAsyncImagePainter(model = figure.imagePath  ),
                                contentDescription = "Saved Figure Image",
                                modifier = Modifier.size(100.dp)
                            )
                        } else {
                            Icon(

                                contentDescription = "question mark",
                                imageVector = ImageVector.vectorResource(id = R.drawable.question_mark),
                                modifier = Modifier.size(100.dp)
                            )


                        }
                    }

                    Row(
                        Modifier.fillMaxWidth().padding(8.dp, 4.dp)

                    ) {
                        AutoText(
                            text = figure.sculpt + '(' + figure.variant + ')',
                            style = MaterialTheme.typography.displayMedium,


                            )
                    }


                }
            }

        }
    }
}


