package com.example.itext_7_pdf.UIs

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.itext_7_pdf.SharedPreffernce.SharedPrefference
import com.example.itext_7_pdf.SharedPreffernce.invoice_info_Details
import com.example.itext_7_pdf.ViewModel.ViewModel1
import com.example.itext_7_pdf.ui.theme.myWhite
import com.example.itext_7_pdf.ui.theme.mydellWhite
import com.example.itext_7_pdf.ui.theme.mytextColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun New_Item(
    navController: NavHostController,
    viewModel: ViewModel1,
    sharedPrefference: SharedPrefference,
    detail_to_save: invoice_info_Details
) {
    Scaffold (
        topBar = {
            TopBar(
                navController = navController,
                Title = "New Item",
                nav_Icon = Icons.Filled.ArrowBack,
                action_Icon = Icons.Filled.Check
            ) {
                Log.i("shivam",viewModel.itemList1.size.toString())
                viewModel.addItemInList(viewModel.Item_Name.value,viewModel.Item_Quantity.value,viewModel.Item_Price.value,viewModel.Amount.value)
                viewModel.Item_Name.value = ""
                viewModel.Item_Quantity.value = "1"
                viewModel.Item_Price.value = "0"
                viewModel.Amount.value = "0.00"
                viewModel.Discount.value = "0"
                viewModel.Tax_Rate.value = "0"
                viewModel.Unit_of_Measure.value = ""
                navController.navigate("create_new_invoice")
                Log.i("shivam",viewModel.itemList1.size.toString())


            }

        },
        containerColor = mydellWhite
    ){
        item(viewModel,detail_to_save)


    }
}

@Composable
fun item(viewModel: ViewModel1, detail_to_save: invoice_info_Details) {
    Card (modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
        .padding(top = 80.dp, start = 15.dp, end = 15.dp, bottom = 250.dp),
        colors = CardDefaults.cardColors(
            containerColor = myWhite
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        )
    ){
        Column (modifier = Modifier.padding(15.dp, top = 20.dp, end = 15.dp, bottom = 20.dp)){

            invoiceNumber(
                invo = "Item Name",
                invoiceNumber = "Enter item name",
                detail_to_save = detail_to_save,
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(20.dp))

            invoiceNumberInput(
                invo = "Item Price",
                invoiceNumber = "$ 0.00",
                detail_to_save = detail_to_save,
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(20.dp))

            invoiceNumberInput(
                invo = "Item Quantity",
                invoiceNumber = "1",
                detail_to_save = detail_to_save,
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(20.dp))

            invoiceNumber(
                invo = "Unit of Measure",
                invoiceNumber = "None",
                detail_to_save = detail_to_save,
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(20.dp))

            invoiceNumberInput(
                invo = "Discount",
                invoiceNumber = "Flat Amount",
                detail_to_save = detail_to_save,
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(20.dp))

            invoiceNumberInput(
                invo = "Tax Amount",
                invoiceNumber = "0%",
                detail_to_save = detail_to_save,
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(20.dp))

            lastBlue(viewModel.Cal_Amount())



        }

    }



}

@Composable
fun lastBlue(amount : String){
    Box(modifier = Modifier
        .background(mytextColor)
        .fillMaxWidth()){
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)) {
            Text(text = "Amount", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = myWhite)
            Text(text = "$ $amount", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = myWhite)
        }

    }
}


@Composable
fun listLayout(
    itemName: String,
    price: String,
    qunantiy: String,
    total: String,
    index: Int
) {
    Surface(modifier = Modifier
        .padding(start = 15.dp, end = 15.dp, top = 7.dp)
        .border(
            0.5f.dp,
            mytextColor,
            RoundedCornerShape(5.dp)
        )
        .clip(RoundedCornerShape(5.dp))
        .fillMaxWidth(),
        color = mydellWhite) {
        Column(horizontalAlignment = Alignment.End, modifier = Modifier.padding(10.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Surface(color = mydellWhite) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "", tint = Color.Black)
                    Text(text = "$itemName", modifier = Modifier.padding(start = 35.dp), )
                }
                Text(text = " $qunantiy * $$price",fontSize = 12.sp, color = Color.Black)
            }
            Text(text = "$$total", fontWeight = FontWeight.Bold, color = Color.Black)

        }
    }
}




