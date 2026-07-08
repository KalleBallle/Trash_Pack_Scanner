package com.example.packscan.UI

import android.content.ClipData
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.core.graphics.toColorInt

import androidx.navigation.NavController
import com.example.packscan.Data.DatabaseProvider
import com.example.packscan.UI.CollectionUi.AutoText
import com.example.packscan.Data.Figure
import com.example.packscan.UI.Navi.NavigationUI
import com.example.packscan.R
import com.example.packscan.UI.CollectionUi.FigureCard
import com.example.packscan.UI.CollectionUi.RarityIcon
import com.example.packscan.UI.CollectionUi.RarityStat
import com.example.packscan.UI.CollectionUi.StatsCard
import com.example.packscan.UI.CollectionUi.TitleTTP

@Composable
fun collectionScreen(navController: NavController) {
    val context = LocalContext.current
val db = remember { DatabaseProvider.getDatabase(context) }
    val figures by db.figureDao().getAllSeries("Trashpack series 1").collectAsState(initial = emptyList())

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.brick),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )



        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(minSize = 100.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 200.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp
        ) {

            item(span = StaggeredGridItemSpan.FullLine) {
                Column(

                )   {
                    TitleTTP("Series 1", Color("#516830".toColorInt()))
                      Spacer(Modifier.height(30.dp))
                    StatsCard(figures)
                }
            }


            item(span = StaggeredGridItemSpan.FullLine) {
                TitleTTP("THE GRUBZ", Color("#516830".toColorInt()),figures.filter { it.team == "THE GRUBZ" })
            }

            items(figures.filter { it.team == "THE GRUBZ"  }) { figure ->

                FigureCard(figure)
            }


            item(span = StaggeredGridItemSpan.FullLine) {
                TitleTTP("HARD RUBBISH", Color("#516830".toColorInt()),figures.filter { it.team == "HARD RUBBISH"  })
            }
            items(figures.filter { it.team == "HARD RUBBISH"  }) { figure ->

                FigureCard(figure)
            }


            item(span = StaggeredGridItemSpan.FullLine) {
                TitleTTP("BIN-SECTS", Color("#516830".toColorInt()),figures.filter { it.team == "BIN-SECTS"  })
            }
            items(figures.filter { it.team == "BIN-SECTS"  }) { figure ->

                FigureCard(figure)
            }

            item(span = StaggeredGridItemSpan.FullLine) {
                TitleTTP("BIN CRITTERS", Color("#516830".toColorInt()),figures.filter { it.team == "BIN CRITTERS" })
            }
            items(figures.filter { it.team == "BIN CRITTERS"  }) { figure ->

                FigureCard(figure)
            }


            item(span = StaggeredGridItemSpan.FullLine) {
                TitleTTP("BIN MONSTERS", Color("#516830".toColorInt()),figures.filter { it.team == "BIN MONSTERS" })
            }
            items(figures.filter { it.team == "BIN MONSTERS"  }) { figure ->

                FigureCard(figure)
            }

            item(span = StaggeredGridItemSpan.FullLine) {
                TitleTTP("BIN-FECTIONS", Color("#516830".toColorInt()), figures.filter { it.team == "BIN-FECTIONS" })
            }
            items(figures.filter { it.team == "BIN-FECTIONS"  }) { figure ->

                FigureCard(figure)
            }
            item(span = StaggeredGridItemSpan.FullLine) {
                TitleTTP("LIMITED EDITION", Color("#516830".toColorInt()), figures.filter { it.team == "LIMITED EDITION" })
            }
            items(figures.filter { it.team == "LIMITED EDITION"  }) { figure ->

                FigureCard(figure)
            }
        }


        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            NavigationUI(navController)
        }
    }
}