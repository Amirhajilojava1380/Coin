package com.example.crypto.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crypto.R

@Composable
fun ItemArrow(modifier: Modifier = Modifier ,data:String) {

    Card(
        modifier = Modifier.size(width = 80.dp , height = 30.dp),
        colors = CardDefaults.cardColors(if(data.toDouble() > 0) Color.Green else Color.Red)
    ) {
        Row(modifier = Modifier.fillMaxSize()){

            Column(
                modifier = Modifier.fillMaxHeight().wrapContentWidth(),
                verticalArrangement = Arrangement.Center
            ){
                Icon(
                    modifier = Modifier.padding(8.dp),
                    painter =  if(data.toDouble() > 0) painterResource(R.drawable.arrow_trend_up) else painterResource(R.drawable.arrow_trend_down),
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Column(
                modifier = Modifier.fillMaxHeight().wrapContentWidth(),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    modifier = Modifier.wrapContentSize(),
                    text = "${data.take(3)}%",
                    color = Color.White,
                    textAlign = TextAlign.Justify,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemArrowText() {
    ItemArrow(data = "12.4")
}