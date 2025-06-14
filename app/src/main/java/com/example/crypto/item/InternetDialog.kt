package com.example.crypto.item

import android.content.Context
import android.content.IntentFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.crypto.R
import com.example.crypto.utils.internet.InternetBroadcastReceiver
import com.example.crypto.viewmodel.MainViewModel

@Composable
fun internetDialog(modifier: Modifier = Modifier , context: Context): MutableState<Boolean> {
    val showDialog = rememberSaveable { mutableStateOf(true) }

    DisposableEffect(Unit) {
        val receiver = InternetBroadcastReceiver { connected ->
            showDialog.value = connected
        }
        val intentFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        context.registerReceiver(receiver, intentFilter)

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }


    if (!showDialog.value){

        val compositionInternet by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.intarnet))
        val progressionInternet by animateLottieCompositionAsState(
            composition = compositionInternet,
            iterations = LottieConstants.IterateForever
        )


        Dialog(
            onDismissRequest = {showDialog.value = false}
        ){

            Box(
                modifier =  Modifier.size(300.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
            ){

                Column(
                    modifier = Modifier.fillMaxSize()
                    , horizontalAlignment = Alignment.CenterHorizontally ,
                    verticalArrangement = Arrangement.Center
                ){

                    Row (
                        modifier = Modifier.fillMaxSize(0.5f)
                    ){
                        LottieAnimation(
                            composition = compositionInternet,
                            progress = progressionInternet ,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Row (
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxHeight(0.2f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            "Pleas Check Internet.",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W500,
                            fontFamily = FontFamily.Monospace
                        )
                    }

                }

            }

        }
        return showDialog
    }else{
        return showDialog
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL_7 ,showBackground = true)
@Composable
private fun InternetDialogTest() {

}