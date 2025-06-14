package com.example.crypto.view.activity


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crypto.item.main.CryptoItemList
import com.example.crypto.item.internetDialog
import com.example.crypto.item.main.PagerItemList
import com.example.crypto.item.main.SquareItemList
import com.example.crypto.model.Data
import com.example.crypto.ui.theme.CryptoTheme
import com.example.crypto.utils.NetWorkResult
import com.example.crypto.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                MainScreen(mainViewModel)
                internetDialog(context = this)
        }
    }


    @Composable
    fun MainScreen(mainViewModel: MainViewModel) {
        var isLoading by remember { mutableStateOf(false) }
        var listItem by remember { mutableStateOf(emptyList<Data>()) }
        var valueSearch by remember { mutableStateOf("") }

        LaunchedEffect(Unit) {
            mainViewModel.getDataCrypto()
        }

        val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

        mainViewModel.readCryptoData.observe(lifecycleOwner){
            when (it) {

                is NetWorkResult.Error -> {
                    isLoading = false
                    Toast.makeText(this, (it).message, Toast.LENGTH_SHORT).show()
                }

                is NetWorkResult.Loading -> {
                    isLoading = true
                }

                is NetWorkResult.Success -> {
                    isLoading = false
                    listItem = it.data.data!!
                }

                null -> {
                    isLoading = false
                }
            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {

            if (listItem.isNotEmpty()) {

                LazyColumn{
                    item{
                        val pageCount = 3
                        val pagerState = rememberPagerState(
                            initialPage = 1,
                            initialPageOffsetFraction = 0f,
                            pageCount = { pageCount },
                        )

                        Text("Top Coins", modifier = Modifier
                            .wrapContentSize()
                            .padding(top = 40.dp, end = 20.dp, start = 20.dp),
                            maxLines = 1,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 30.sp
                        )

                        HorizontalPager(
                            contentPadding = PaddingValues(horizontal = 36.dp),
                            state = pagerState,
                            )

                        { data ->
                            PagerItemList(data = listItem[data], context = this@MainActivity)
                        }
                    }

                    item{
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                            ){
                            Text("Charts", modifier = Modifier
                                .wrapContentSize()
                                .padding(top = 16.dp, end = 20.dp, start = 20.dp),
                                maxLines = 1,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                fontSize = 30.sp
                            )

                            TextField(

                                value = valueSearch,
                                onValueChange = { valueSearch = it },
                                modifier = Modifier
                                    .fillMaxWidth(0.6f)
                                    .height(63.dp)
                                    .padding(end = 20.dp, top = 12.dp)
                                ,
                                placeholder = {
                                    Text(text = "Search here...")
                                },
                                shape = RoundedCornerShape(30.dp)
                                ,colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                )
                            )


                        }

                    }

                    items(
                        items = if (valueSearch.isEmpty()) listItem.take(18) else   listItem.filter { it.name!!.contains(valueSearch) }, // data list 18
                        key = { it.id!! },


                    ) { data ->
                        CryptoItemList(
                            data = data , context = this@MainActivity
                        )
                    }


                    itemsIndexed(listItem.chunked(2).drop(18)){ _, pair ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                        ) {
                            pair.filter { it.name!!.contains(valueSearch) }.forEach{ data ->
                                Box(
                                    modifier = Modifier.weight(weight=1f, fill = true),
                                ) {

                                    if (valueSearch.isEmpty()) SquareItemList(data = data , context = this@MainActivity)

                                }

                            }
                        }
                    }
                }


            }else{

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("data is Not show")
                }
                if (internetDialog(context = this).value && listItem.isNotEmpty()) {mainViewModel.getDataCrypto()}

            }
        }
    }

    @Preview(showSystemUi = true, device = Devices.PIXEL_7)
    @Composable
    fun GreetingPreview() {
        CryptoTheme {

            MainScreen(mainViewModel)

        }
    }



}


