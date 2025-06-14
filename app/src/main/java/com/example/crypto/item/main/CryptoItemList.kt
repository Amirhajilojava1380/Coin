package com.example.crypto.item.main

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crypto.R
import com.example.crypto.model.Data
import com.example.crypto.utils.getDrawableIdForCoin
import com.example.crypto.view.activity.DetailActivity

@Composable
fun CryptoItemList(modifier: Modifier = Modifier , data:Data , context:Context) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.12f)
            .padding(top = 16.dp)
            .clickable {
                context.startActivity(Intent(context, DetailActivity::class.java).apply {

                    putExtra("model" , data)

                }, null)
            }
            ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .padding(10.dp)
                    .size(80.dp)
                ,
                painter = painterResource(getDrawableIdForCoin(data.symbol!!)),
                contentDescription = null,
            )
        }

        Column(
            modifier = Modifier
                .wrapContentWidth()
                .height(100.dp)
                .padding(start = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = data.symbol!!,
                maxLines = 1,
                color = colorResource(R.color.blackLite),
                fontSize = 20.sp,
                modifier = Modifier.wrapContentSize(),
            )
            Text(
                text = "${data.changePercent24Hr!!.take(4)}%",
                maxLines = 1,
                lineHeight = 1.sp,
                color = colorResource(R.color.blackLite2),
                fontSize = 18.sp,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 6.dp),
            )
        }

        Column(
            modifier = Modifier
                .height(100.dp)
                .wrapContentWidth()
                .padding(start = 40.dp),
            verticalArrangement = Arrangement.Center
        ) {
            PerformanceChart(data = data.changePercent24Hr!!,
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(0.4f)
            )
        }

        Column(
            modifier = Modifier
                .height(100.dp)
                .padding(start = 40.dp)
                .wrapContentWidth()
            ,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$${data.priceUsd}",
                maxLines = 1,
                color = colorResource(R.color.blackLite),
                fontSize = 17.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Monospace
            )
        }

    }

}

@Preview(showBackground = true , showSystemUi = true , device = Devices.PIXEL_7 )
@Composable
fun TextHi(modifier: Modifier = Modifier) {
}


