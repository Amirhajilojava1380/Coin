package com.example.crypto.item.main

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crypto.R
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.crypto.model.Data
import com.example.crypto.utils.getDrawableIdForCoin
import com.example.crypto.view.activity.DetailActivity


@Composable
fun PagerItemList(modifier: Modifier = Modifier , data:Data , context: Context) {
    Card(
        modifier = Modifier
            .padding(top = 30.dp , bottom = 4.dp , end = 5.dp , start = 5.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.25f)
        ,

        colors = CardDefaults.cardColors(colorResource(R.color.redLite))
    ) {

        Column(

            modifier = Modifier.padding(30.dp)

        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
                ,
                horizontalArrangement = Arrangement.SpaceBetween
            ){

                Text(
                    text = "$${data.priceUsd!!.take(10)}",
                    maxLines = 1,
                    color = colorResource(R.color.white),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxHeight().wrapContentWidth(),
                )

                Icon(
                    modifier = Modifier.fillMaxSize(0.3f),
                    painter = painterResource(getDrawableIdForCoin(data.symbol!!)),
                    contentDescription = null,
                    tint = Color.Black
                )


            }

            Row (
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight(0.2f)

                ,
                horizontalArrangement = Arrangement.SpaceBetween){


                Text(
                    text = "Your balance is equivalent",
                    maxLines = 1,
                    color = colorResource(R.color.white),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.fillMaxHeight().wrapContentWidth(),
                )
            }

            Row (
                modifier = Modifier
                    .padding(top = 50.dp)
                    .wrapContentHeight()
                    .fillMaxWidth()
                    , horizontalArrangement = Arrangement.Start
            ){
                OutlinedButton({

                    context.startActivity(Intent(context, DetailActivity::class.java).apply {

                        putExtra("model" , data)

                    })

                } , modifier = Modifier.padding(end = 8.dp)) {
                    Text("See")
                }
                Button ({

                    context.startActivity(Intent(context, DetailActivity::class.java).apply {

                        putExtra("model" , data)

                    })

                }) {
                    Text("Deposit")
                }

            }


        }

    }

}

@Preview(showSystemUi = true, device = Devices.PIXEL_7 ,showBackground = true)
@Composable
private fun PagerItemListText() {

}
