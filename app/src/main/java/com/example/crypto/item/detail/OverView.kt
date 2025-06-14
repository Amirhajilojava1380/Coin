package com.example.crypto.item.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crypto.R
import com.example.crypto.model.Data

@Composable
fun OverView(modifier: Modifier = Modifier,data: Data) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.bg_card),
                contentDescription = null,
                contentScale = ContentScale.Crop, // برای پر کردن کامل فضای کارت
                modifier = Modifier.fillMaxWidth().fillMaxHeight()
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth().wrapContentHeight()
            ) {

                Text(
                    "Overview",
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 25.dp, end = 14.dp, start = 14.dp),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.005f)
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .background(Color.LightGray)
                )

                TextNewCard(dataName = "Price:" , dataPrice = data.priceUsd!!)
                TextNewCard(dataName = "MarketCap:" , dataPrice = data.marketCapUsd!!)
                TextNewCard(dataName = "Vwap24Hr:" , dataPrice = data.vwap24Hr!!)
                TextNewCard(dataName = "supply:" , dataPrice = data.supply!!)
                TextNewCard(dataName = "volume24Hr:" , dataPrice = data.volumeUsd24Hr!!)

            }
        }

    }

}

@Composable
fun TextNewCard(modifier: Modifier = Modifier,dataName:String,dataPrice: String) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(modifier = Modifier
            .fillMaxWidth(0.5f)
            .wrapContentHeight()) {
            Text(
                dataName,
                modifier = Modifier
                    .wrapContentHeight().fillMaxWidth()
                    .padding(14.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                textAlign = TextAlign.Left
            )
        }

        Column(modifier = Modifier
            .fillMaxWidth(1f)
            .wrapContentHeight()){
            Text(
                "$${dataPrice.take(10)}",
                modifier = Modifier
                    .wrapContentHeight().fillMaxWidth()
                    .padding(14.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                color = Color.White
            )
        }
    }
}