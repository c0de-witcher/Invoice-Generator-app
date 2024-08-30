package com.example.itext_7_pdf.UIs

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.DialogHost
import androidx.navigation.compose.rememberNavController
import com.example.itext_7_pdf.R
import com.example.itext_7_pdf.SharedPreffernce.SharedPrefference
import com.example.itext_7_pdf.SharedPreffernce.invoice_info_Details
import com.example.itext_7_pdf.ViewModel.ViewModel1
import com.example.itext_7_pdf.ui.theme.myBlue
import com.example.itext_7_pdf.ui.theme.myGrey
import com.example.itext_7_pdf.ui.theme.myWhite
import com.example.itext_7_pdf.ui.theme.mydellWhite
import com.example.itext_7_pdf.ui.theme.myfontGrey
import com.example.itext_7_pdf.ui.theme.mytextColor





@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PageLayout(
    navController: NavHostController,
    viewModel: ViewModel1,
    sharedPrefference: SharedPrefference,
    detail_to_save: invoice_info_Details,
) {
    Scaffold (
        containerColor = mydellWhite,
        topBar = {
            TopBar(navController,"New Invoice",Icons.Filled.ArrowBack,Icons.Outlined.Home,
                {})


        },
        bottomBar = {
            bottombar()
        }
    ) {

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 60.dp, end = 15.dp, bottom = 60.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            InoiveNumber(navController,sharedPrefference,detail_to_save,viewModel)
            template(navController,viewModel)
            info(navController,viewModel)
            items(Modifier.padding(15.dp),navController,viewModel,detail_to_save)
            lastMessage(navController,viewModel)
        }
    }


}

@Composable
fun lastMessage(navController: NavHostController, viewModel: ViewModel1) {
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 90.dp), horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Tell us your advice about this page", color = mytextColor)
        Text(text = "GIVE FEEDBACK", color = myBlue, modifier = Modifier
            .padding(top = 10.dp)
            .clickable { })
    }
}

@Composable
fun items(modifier: Modifier, navController: NavHostController,viewModel: ViewModel1,detail_to_save: invoice_info_Details) {
    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp, bottom = 60.dp),
        colors = CardDefaults.cardColors(
            containerColor = myWhite
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        )

        ) {
        Column {
            Row (modifier, verticalAlignment = Alignment.CenterVertically){
                Image(painter = painterResource(id = R.drawable.online_shopping), contentDescription = " Items")
                Text(text = "Items", color = mytextColor, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 15.dp), fontSize = 20.sp)
            }



           if (viewModel.itemList1 != null){
               viewModel.itemList1.forEachIndexed { index, itemList ->
                   Log.i("shivam ","index $index")



                   if (itemList != null) {
                       //viewModel.sub_total.value = (viewModel.sub_total.value.toDouble() + itemList.total.toDouble()).toString()
                       listLayout(itemList.itemName,itemList.price,itemList.Qunantiy,itemList.total,viewModel,navController,detail_to_save,index)
                   }
               }
           }





            OutlinedButton(onClick = { navController.navigate("NewItem") },
                shape = RoundedCornerShape(5.dp),
                modifier = modifier,
                contentPadding = PaddingValues(start = 10.dp, end = 15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = mydellWhite
                ),

                ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "", tint = Color.Black)
                    Text(text = "Add Item", color = myfontGrey, modifier = Modifier.padding(start = 10.dp))
                }


            }


            Surface (color = mydellWhite,shape = RoundedCornerShape(5.dp), modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
                .fillMaxWidth()
                .border(
                    1.dp, myGrey,
                    RoundedCornerShape(5.dp)
                )
                .clip(RoundedCornerShape(5.dp))
            ){
                Row (verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding( top = 5.dp, bottom = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween

                    ){
                    Text(text = "Sub total", fontWeight = FontWeight.Bold, color = myfontGrey,  modifier = Modifier.padding(start = 10.dp, top = 5.dp, bottom = 5.dp))
                    Text(text = viewModel.cal_sub_Total(), fontWeight = FontWeight.Bold, color = myfontGrey,  modifier = Modifier.padding(end = 10.dp, top = 5.dp, bottom = 5.dp))

                }
            }












            Button(onClick = {
                viewModel.checkDiscount.value = true


            },modifier = Modifier
                .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues(start = 25.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
                shape = RoundedCornerShape(0.dp)

            ) {

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Surface (color = Color.Transparent){
                        Image(painter = painterResource(id =  R.drawable.flash_sale), contentDescription = "")
                        Text(text = "Discount", fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.padding(start = 40.dp), color = Color.Black)
                    }
                    if (true){
                        Icon(imageVector =Icons.Filled.KeyboardArrowRight, contentDescription = "Enter", tint = Color.Black, modifier = Modifier)
                    }
                }
            }
            if (viewModel.checkDiscount.value){
                Dialog_Box(title = "Discount",viewModel,detail_to_save,"Enter Discount","0%",false,"","","D")
            }


/*
                "Tax Name", "Enter tax name",
                true,
                "Tax Rate", "0%", "T"

            */

            Button(onClick = {
                viewModel.checkTax.value = true

            },modifier = Modifier
                .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues(start = 25.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
                shape = RoundedCornerShape(0.dp)

            ) {

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Surface (color = Color.Transparent){
                        Image(painter = painterResource(id =  R.drawable.tax), contentDescription = "")
                        Text(text = "Tax", fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.padding(start = 40.dp), color = Color.Black)
                    }
                    if (true){
                        Icon(imageVector =Icons.Filled.KeyboardArrowRight, contentDescription = "Enter", tint = Color.Black, modifier = Modifier)
                    }
                }
            }
            if ( viewModel.checkTax.value){
                Dialog_Box(title ="Tax",viewModel,detail_to_save,"Tax Name","Enter tax name",true,"Tax Amount","","T")
            }



               /* "Shipping Amount", "$0.00",
                false,
                "", "", "S"*/



            Button(onClick = {
                viewModel.checkShipping.value = true

            },modifier = Modifier
                .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues(start = 25.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
                shape = RoundedCornerShape(0.dp)

            ) {

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Surface (color = Color.Transparent){
                        Image(painter = painterResource(id =  R.drawable.fast_delivery), contentDescription = "")
                        Text(text ="Shipping", fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.padding(start = 40.dp), color = Color.Black)
                    }
                    if (true){
                        Icon(imageVector =Icons.Filled.KeyboardArrowRight, contentDescription = "Enter", tint = Color.Black, modifier = Modifier)
                    }
                }
            }

            if (viewModel.checkShipping.value ){
                Dialog_Box(title ="Shipping",viewModel,detail_to_save,"Shipping Amount","$0.00",false,"","","S")
            }















            Row (modifier = Modifier
                .fillMaxWidth()
                .background(mytextColor)
                .padding(start = 25.dp, end = 20.dp, top = 10.dp, bottom = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){

                Text(text = "Total", color = myWhite, fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.padding(start = 0.dp))
                Text(text = "$ 0.00", color = myWhite, fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.padding(start = 10.dp))

            }

        }

    }
}







@Composable
fun nav_ooptions(Title :String , image : Int,icon : ImageVector,action_control : Boolean,onClick : ()->Unit,paddingValues: PaddingValues){
    Button(onClick = { onClick() },modifier = Modifier
        .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = paddingValues,
        shape = RoundedCornerShape(0.dp)

    ) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Surface (color = Color.Transparent){
                Image(painter = painterResource(id = image), contentDescription = "")
                Text(text = Title, fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.padding(start = 40.dp), color = Color.Black)
            }
            if (action_control){
                Icon(imageVector = icon, contentDescription = "Enter", tint = Color.Black, modifier = Modifier)
            }
        }
    }
}

@Composable
fun info(navController: NavHostController, viewModel: ViewModel1) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = myWhite
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column{

            nav_ooptions(
                Title = "Business Info",
                image = R.drawable.sales,
                icon = Icons.Filled.KeyboardArrowRight,
                action_control = true,
                {
                    navController.navigate("Business_Info")
                },
                PaddingValues(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp)
            )

            nav_ooptions(
                Title = "Client Info",
                image = R.drawable.man,
                icon = Icons.Filled.KeyboardArrowRight,
                action_control = true,
                onClick = {  navController.navigate("NewClient") },
                paddingValues = PaddingValues(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp)
            )

        }

    }
}

@Composable
fun template(navController: NavHostController, viewModel: ViewModel1) {
    Card (colors = CardDefaults.cardColors(
        containerColor = myWhite
    ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            ,

        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        ),
        shape = RoundedCornerShape(10.dp),
    ){
        nav_ooptions(
            Title = "Templates",
            image = R.drawable.templates,
            icon = Icons.Filled.KeyboardArrowRight,
            action_control = true,
            onClick = { /*TODO*/ },
            paddingValues = PaddingValues(15.dp)
        )

    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
 fun InoiveNumber(
    navController: NavHostController,
    sharedPrefference: SharedPrefference,
    detail_to_save: invoice_info_Details,
    viewModel: ViewModel1
) {
       Card (
           colors = CardDefaults.cardColors(
               containerColor = myWhite
           ),
           elevation = CardDefaults.elevatedCardElevation(
               defaultElevation = 5.dp
           ),
           modifier = Modifier.padding(top = 30.dp)
       ){
           Row (modifier = Modifier
               .clickable { navController.navigate("Invoice_Info") }
               .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp)
               .fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically){
               Column {
                   Text(text = "INV00002", fontWeight = FontWeight.Bold, fontSize = 32.sp, color = mytextColor)
                   Text(text = "Created on ${viewModel.Creation_Date.value} ", color = Color.Gray ,modifier = Modifier.padding(top = 5.dp), fontSize = 11.sp)
                   Text(text = "Due on ${viewModel.Due_Date.value}", color = Color.Gray, modifier = Modifier.padding(top = 5.dp), fontSize = 11.sp)
               }
               Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = "enter invoice", tint = Color.Black)
           }
       }



}


@Composable
fun bottombar() {
    BottomAppBar (
        containerColor = myWhite,
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp),
        tonalElevation = 5.dp
    ){
        Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){

            Button(onClick = { /*TODO*/ },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = myBlue
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 2.dp
                )

            ) {
                Text(text = "Save", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController,
    Title: String,
    nav_Icon: ImageVector?,
    action_Icon:ImageVector,
    onClick_Action : ()-> Unit
) {
    TopAppBar(title = { Text(text = Title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 10.dp), fontSize = 20.sp)},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = myBlue,
            titleContentColor = myWhite,
            navigationIconContentColor = myWhite
        ),
        navigationIcon = {
           IconButton(onClick = { navController.navigate("Home_Screen")}) {
               Icon(imageVector = if (nav_Icon == null) {
                   Icons.Filled.ArrowBack
               } else {
                   nav_Icon
               }, contentDescription = "BAck")
           }
        },
        actions = {
           IconButton(onClick = { onClick_Action() }) {
               Icon(imageVector = if (action_Icon == null){Icons.Outlined.Home} else {action_Icon}, contentDescription = "", tint = myWhite)
           }
        }
        )
}

