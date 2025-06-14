package com.example.crypto.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crypto.R
import com.example.crypto.item.detail.ChartNew
import com.example.crypto.item.detail.ItemRowTable
import com.example.crypto.item.detail.OverView
import com.example.crypto.item.internetDialog
import com.example.crypto.model.Data
import com.example.crypto.model.Exchange
import com.example.crypto.utils.NetWorkResult
import com.example.crypto.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class DetailActivity : ComponentActivity() {

    private lateinit var data: Data
    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            internetDialog(context = this)
            data = intent.getParcelableExtra("model")!!

            Greeting(data = data)
        }
    }

    @Composable
    fun Greeting(modifier: Modifier = Modifier,data: Data) {
        MoreItem(mainViewModel)
    }



    @Composable
    fun ActionBarNew(modifier: Modifier = Modifier,data: Data) {
        TopAppBar(
            colors = TopAppBarColors(
                containerColor = Color.White,
                scrolledContainerColor = Color.Black,
                navigationIconContentColor = Color.Black,
                titleContentColor = Color.Black,
                actionIconContentColor = Color.Black
            ),
            title = { Text(
                data.name+" (${data.symbol})",
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 16.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ) },
            navigationIcon = {
                Icon(
                    modifier = Modifier
                        .size(25.dp)
                        .clickable { finish() },
                    painter = painterResource(R.drawable.angle_left),
                    contentDescription = null
                )
            },

        )
    }

    @Composable
    fun MoreItem(mainViewModel: MainViewModel) {

        var isLoading by remember { mutableStateOf(false) }
        var listItem  by remember { mutableStateOf(emptyList<Exchange>()) }

        LaunchedEffect(Unit) {
            mainViewModel.getExchangesData()
        }

        val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

        mainViewModel.readExchangesData.observe(lifecycleOwner){
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
                    listItem = it.data.exchanges!!
                }

                null -> {}
            }
        }


        if (!isLoading){

            if (listItem.isNotEmpty()) {
                LazyColumn{
                    item() {
                        ActionBarNew(data = data)
                        ChartNew(data = data)
                        OverView(data = data)
                        Text(
                            "Exchanges",
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    items(items = listItem, key = { it.exchangeId!! }) {
                        ItemRowTable(data = it, context = this@DetailActivity)
                    }

                }
            }else{
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("data is Not show")
                }
                if (internetDialog(context = this).value)
                    mainViewModel.getExchangesData()
            }
        }


    }

}

