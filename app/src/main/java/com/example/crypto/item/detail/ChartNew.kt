package com.example.crypto.item.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crypto.item.ItemArrow
import com.example.crypto.model.Data
import com.jaikeerthick.composable_graphs.composables.bar.BarGraph
import com.jaikeerthick.composable_graphs.composables.bar.model.BarData
import com.jaikeerthick.composable_graphs.composables.bar.style.BarGraphColors
import com.jaikeerthick.composable_graphs.composables.bar.style.BarGraphStyle

@Composable
fun ChartNew(modifier: Modifier = Modifier,data: Data) {
    val dataChart = listOf(
        BarData(x = "1", y = 200), BarData(x = "2", y = 140),
        BarData(x = "3", y = 220), BarData(x = "4", y = 240),
        BarData(x = "5", y = 20), BarData(x = "6", y = 100)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)

    ) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1f)
            .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "$${data.priceUsd!!.take(10)}",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
            )

            ItemArrow(data = data.changePercent24Hr!!)
        }


        BarGraph(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp),
            data = dataChart,
            style = BarGraphStyle(colors = BarGraphColors(Color.Green))
        )
    }
}
