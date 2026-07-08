package com.example.packscan.UI.CollectionUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.R
import androidx.core.graphics.toColorInt

import com.example.packscan.Data.Figure


@Composable
fun RarityStat(
    rarity : String,
    subFigures :  List<Figure>

)
{
    Card(
        colors = CardDefaults.cardColors(
        containerColor = Color("#3A3A3C".toColorInt())
    )) {
        Row(

            verticalAlignment = Alignment.CenterVertically
            , modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        {
            val comp = subFigures.count { it.collected }
            val total = subFigures.size
            var color : Color = Color.White
            val percentage = if (total > 0) { comp * 100 / total} else 0

            RarityIcon(rarity)
            Spacer(Modifier.width(12.dp))

            if (comp == total)
            {
            color = Color.Green
            }
            Text( rarity + ": " + comp.toString() +'/' +total.toString()
                    +" ( " +(percentage) +'%' + ')'
            , fontSize = 20.sp
            , color = color
                )
        }

    }

}

@Composable
fun GroupStats(
    subFigures : List<Figure>
)
{

        val comp = subFigures.count { it.collected }
        val total = subFigures.size
        var color : Color = Color.White
        if (comp == total)
        {
            color = Color.Green
        }
        Text(
            fontSize = 20.sp
            , color = color
            , text = comp.toString() + '/' + total.toString()
        )


    }




@Composable
fun StatsCard(
    figures : List<Figure>
)
{
    Card(
        modifier = Modifier.fillMaxWidth()
        ,   colors = CardDefaults.cardColors(
            containerColor = Color("#1C1C1E".toColorInt())
        )
    ) {

        Column(
            Modifier.padding(8.dp,16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        )
        {

            Row()
            {
                Card(colors = CardDefaults.cardColors(
                    containerColor = Color("#3A3A3C".toColorInt())
                )

                )
                {
                    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)
                        , horizontalArrangement = Arrangement.Center) {
                        val totalAmount = figures.size
                        val totalComp = figures.count { it.collected}
                        var color : Color = Color.White
                        val percentage = if (totalAmount > 0) { totalComp * 100 / totalAmount} else 0
                        if (totalComp == totalAmount)
                        {
                            color = Color.Green
                        }
                        Text(
                            text = "Total completion: " + totalComp.toString() + '/' + totalAmount.toString()
                                    + " ( " + percentage + '%' + ')',
                            fontSize = 25.sp
                            , color = color
                        )
                    }
                }
            }
            RarityStat("Common",figures.filter { it.rarity == "Common"})
            RarityStat("Rare",figures.filter { it.rarity == "Rare"})
            RarityStat("Ultra Rare",figures.filter { it.rarity == "Ultra Rare"})
            RarityStat("Special Edition",figures.filter { it.rarity == "Special Edition"})
            RarityStat("Limited Edition",figures.filter { it.rarity == "Limited Edition"})






        }

    }

}