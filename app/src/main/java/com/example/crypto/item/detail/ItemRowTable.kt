package com.example.crypto.item.detail


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crypto.model.Exchange

@Composable
fun ItemRowTable(modifier: Modifier = Modifier, data:Exchange,context: Context ) {

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.14f)
            .padding(vertical = 4.dp , horizontal = 15.dp).clickable {
                openWebPage(context,data.exchangeUrl!!)
            },
        backgroundColor = Color.White,
        elevation = 1.dp,
        shape = RoundedCornerShape(8.dp)
        ){

        Row(
            modifier = Modifier.fillMaxSize()

        ){
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.24f)

                , verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Box (
                    modifier = Modifier
                        .fillMaxSize(0.8f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Green)
                        ,
                    contentAlignment = Alignment.Center,
                ){
                    Text(
                        text = data.rank!!.take(2),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }



            Column (
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(1f)
                    .padding(4.dp),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = data.name!!.take(10),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
                Row {
                    Text(
                        text = "PercentTotalVolume : ",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.LightGray
                    )
                    Text(
                        text = "$${data.percentTotalVolume?.take(10)}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }
                Row {
                    Text(
                        text = "volumeUsd : ",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.LightGray
                    )
                    Text(
                        text = "$${data.volumeUsd?.take(10)}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }
                
            }
        }

    }

}




@Preview(showSystemUi = true, device = Devices.PIXEL_7)
@Composable
private fun ItemRowTableTest() {
}

fun openWebPage(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

