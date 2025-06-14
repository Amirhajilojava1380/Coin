package com.example.crypto.item.main

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crypto.item.ItemArrow
import com.example.crypto.model.Data
import com.example.crypto.utils.getDrawableIdForCoin
import com.example.crypto.view.activity.DetailActivity

@Composable
fun SquareItemList(modifier: Modifier = Modifier , data :Data , context: Context) {

    Card(
        Modifier
            .fillMaxWidth(0.97f)
            .fillMaxHeight(0.18f)
            .padding(5.dp)
            .clickable {
                context.startActivity(Intent(context, DetailActivity::class.java).apply {

                    putExtra("model" , data)

                })
            }
        ,
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(Color.White)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight()
                    .padding(end = 6.dp)
            ){

                Icon(
                    modifier = Modifier.fillMaxSize(0.7f),
                    painter = painterResource(getDrawableIdForCoin(data.symbol!!)),
                    contentDescription = null,
                    tint = Color.Black
                )

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight()

            ){
                Text(
                    text = data.symbol!!.uppercase().take(3),
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = data.name!!.take(5),
                    color = Color.DarkGray,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight()
                    .padding(4.dp)
            ){
                ItemArrow(data = data.changePercent24Hr!!)
            }

        }

        Spacer(
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth(0.9f)
                .height(0.5.dp)
                .align(Alignment.CenterHorizontally)
        )


        Row (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
                .background(Color.White)
                .padding(10.dp)
            , horizontalArrangement = Arrangement.SpaceBetween
        ){

            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight()
                    .padding(4.dp)
            ){
                Text(
                    text = "Portfolio",
                    color = Color.LightGray,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = "$${data.priceUsd!!.take(8)}",
                    color = Color.DarkGray,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

            }


        }

    }

}

@Preview(showBackground = true , showSystemUi = true , device = Devices.PIXEL_7 )
@Composable
private fun SquareItemListText() {
    Row (modifier = Modifier.fillMaxWidth()) {}
}