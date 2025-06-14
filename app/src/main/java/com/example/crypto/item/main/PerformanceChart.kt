package com.example.crypto.item.main

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

private fun getValuePercentageForRange(value: Float, max: Float, min: Float) =
    (value - min) / (max - min)

@Composable
    fun PerformanceChart(modifier: Modifier = Modifier,data:String) {

        val list:List<Float>
        val dataNew = data.toDouble()
        val lineColor :Color
        if (dataNew < 0){ // red
            list = listOf(0f, -8f, -5f, -12f , -8f, -16f , -10f , -18f)
            lineColor = Color.Red
        }else if (dataNew > 0){ // green
            list = listOf(0f, 8f, 5f, 12f , 8f, 16f , 10f , 18f)
            lineColor = Color.Green
        }else{
            list = listOf(0f,0f)
            lineColor = Color.Green
        }

        val zipList: List<Pair<Float, Float>> = list.zipWithNext()

        Row(modifier = modifier) {
            val max = list.max()
            val min = list.min()

            for (pair in zipList) {

                val fromValuePercentage = getValuePercentageForRange(pair.first, max, min)
                val toValuePercentage = getValuePercentageForRange(pair.second, max, min)

                Canvas(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    onDraw = {
                        val fromPoint = Offset(x = 0f, y = size.height.times(1 - fromValuePercentage)) // <-- Use times so it works for any available space
                        val toPoint =
                            Offset(x = size.width, y = size.height.times(1 - toValuePercentage)) // <-- Also here!

                        drawLine(
                            color = lineColor,
                            start = fromPoint,
                            end = toPoint,
                            strokeWidth = 3f
                        )
                    })
            }
        }
    }


@Preview
@Composable
private fun Text() {

    PerformanceChart(data = "-2")
}