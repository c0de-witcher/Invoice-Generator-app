package com.example.itext_7_pdf.UIs

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.itext_7_pdf.LoadData.LIstView
import com.example.itext_7_pdf.LoadData.MapAddressToList
import com.example.itext_7_pdf.LoadData.getInvoice
import com.example.itext_7_pdf.R
import com.example.itext_7_pdf.SharedPreffernce.SharedPrefference
import com.example.itext_7_pdf.SharedPreffernce.invoice_info_Details
import com.example.itext_7_pdf.ViewModel.ViewModel1
import com.example.itext_7_pdf.ui.theme.myBlue
import com.example.itext_7_pdf.ui.theme.myGreen
import com.example.itext_7_pdf.ui.theme.myWhite
import com.example.itext_7_pdf.ui.theme.mydellWhite

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePAge(
    context: Context,
    navController: NavHostController,
    viewModel: ViewModel1,
    sharedPrefference: SharedPrefference,
    detail_to_save: invoice_info_Details
){
    Scaffold(modifier = Modifier.fillMaxSize(),
        containerColor = mydellWhite,
        topBar = { TopBAr1()},
        bottomBar = { BottomAppbar()},
        floatingActionButton = {FloatingActionButtonw(navController) },
        floatingActionButtonPosition = FabPosition.EndOverlay


        ){

        val list = getInvoice(context)
        val LoadDataList = MapAddressToList(list)


        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp, start = 10.dp)){
            ScreenContent()
            LIstView(list = LoadDataList)

        }
    }

}

@Composable
fun ScreenContent() {

        Row (modifier = Modifier
            .fillMaxWidth()){
            Card(modifier = Modifier
                .padding(10.dp)
                .width(150.dp)
                .height(120.dp)
                ,
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 5.dp,
                ),
                colors = CardDefaults.cardColors(
                    containerColor = myWhite
                )) {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Total Invoice", color = Color.Black, modifier = Modifier.padding(20.dp))
                    Text(text = "100", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                }

            }
            Card(modifier = Modifier
                .padding(10.dp)
                .width(150.dp)
                .height(120.dp),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 5.dp,
                ),
                colors = CardDefaults.cardColors(
                    containerColor = myWhite
                )) {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Total Money", color = Color.Black, modifier = Modifier.padding(20.dp, bottom = 10.dp, top = 20.dp, end = 20.dp))
                    Text(text = "3800", fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(10.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = myGreen)
                }

            }

        }


}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FloatingActionButtonw(navController: NavHostController) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.circle_wave))

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Box (modifier = Modifier
            .offset(20.dp)
            .clip(CircleShape)
            .background(Color.Transparent)
            //.size(100.dp)
            , contentAlignment = Alignment.Center
           ){
            LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever, modifier = Modifier.size(150.dp))
                FloatingActionButton(onClick = { navController.navigate("create_new_invoice") },
                    shape = CircleShape,
                    containerColor = myBlue,
                    modifier = Modifier
                        .padding(15.dp)
                        .size(70.dp)

                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Floating action Button", tint = myWhite)

                }


        }
    }
}

@Composable
fun BottomAppbar() {
    BottomAppBar(modifier = Modifier.fillMaxWidth(),
        containerColor = myWhite) {}
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBAr1(){
    Row (verticalAlignment = Alignment.Bottom, modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)){
        TopAppBar(title = { Text(text = "INVOICE", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 20.dp))},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = myBlue,
                titleContentColor = myWhite
            ),
            modifier = Modifier.fillMaxWidth(),
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu", tint = myWhite)
                }
            },
            actions = {
                IconButton(onClick = { /*TODO*/ },
                    modifier = Modifier.padding(end = 20.dp)) {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search", tint = myWhite, )
                }
            }
        )
    }
}