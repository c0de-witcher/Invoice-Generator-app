package com.example.itext_7_pdf.UIs

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.itext_7_pdf.SharedPreffernce.invoice_info_Details
import com.example.itext_7_pdf.ViewModel.ViewModel1
import com.example.itext_7_pdf.ui.theme.myBlue
import com.example.itext_7_pdf.ui.theme.myWhite
import com.example.itext_7_pdf.ui.theme.mytextColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun itemDetail_View(
    navController: NavHostController,
    viewModel: ViewModel1,
    detail_to_save: invoice_info_Details,
) {
    Scaffold (
        topBar = {
            TopBar_two_action(navController = navController, Title = "Edit Item", nav_Icon = Icons.Filled.ArrowBack,
            action_Icon = Icons.Outlined.Delete,
                {
                    Log.i("shivam","navii")
                    navController.navigate("create_new_invoice")
                    if (viewModel.index != null) {
                        Log.i("shivam","hello")
                        viewModel.deleteDataAtIndex(viewModel.index!!.toInt())
                        viewModel.index = null
                    }







                        /* if ( index!!.toInt() == 0){
                        Log.i("shivam ","index $index and size of list  remove ${viewModel.itemList1.size}")
                        Log.i("shivam ","index $index and item ${viewModel.itemList1[0]}")
                        viewModel.itemList1.removeAt(0)
                        Log.i("shivam ","index $index and item ${viewModel.itemList1[0]}")

                       // viewModel.itemList1.removeAt(index!!.toInt())
                    }*/



        },Icons.Filled.Check,
                {
            viewModel.replace_item_in_list(viewModel.index!!.toInt())


                    viewModel.Edit_Item_Name.value = ""
                    viewModel.Edit_Item_Price.value = "0"
                    viewModel.Edit_Item_Quantity.value = "1"
                    viewModel.Edit_Unit_of_Measure.value = ""
                    viewModel.Edit_Discount.value = "0"
                    viewModel.Edit_Tax_Rate.value = "0"
                    viewModel.Edit_Amount.value = "0.00"
                    navController.navigate("create_new_invoice")

                })
        }
    ){
        ItemDetail_view_Content(viewModel,detail_to_save,viewModel.index)

    }
}

@Composable
fun ItemDetail_view_Content(
    viewModel: ViewModel1,
    detail_to_save: invoice_info_Details,
    index: Int?
) {


           itemToEdit(viewModel = viewModel, detail_to_save = detail_to_save,index)



}

@Composable
fun itemToEdit(viewModel: ViewModel1, detail_to_save: invoice_info_Details, index: Int?) {
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
            viewModel.asign_value_to_Edit_Item_list(index!!)


            Edit_List_View(
                invo = "Item Name",
                invoiceNumber = "Enter item name",
                detail_to_save = detail_to_save,
                viewModel = viewModel,
                key = "Edit_Item_Name"
            )
            Spacer(modifier = Modifier.height(20.dp))

            Edit_List_View(
                invo = "Item Price",
                invoiceNumber = "Enter item Price",
                detail_to_save = detail_to_save,
                viewModel = viewModel,
                key = "Edit_Item_Price"
            )
            Spacer(modifier = Modifier.height(20.dp))
            
            Edit_List_View(
                invo = "Item Quantity",
                invoiceNumber = "Enter Item Quantity",
                detail_to_save = detail_to_save,
                viewModel = viewModel,
                key = "Edit_Item_Quantity"
            )
            Spacer(modifier = Modifier.height(20.dp))


            Edit_List_View(
                invo = "Unit of Measure",
                invoiceNumber = "Enter Unit of Measure",
                detail_to_save = detail_to_save,
                viewModel = viewModel,
                key = "Edit_Unit_of_Measure"
            )
            Spacer(modifier = Modifier.height(20.dp))


            Edit_List_View(
                invo = "Discount",
                invoiceNumber = "Enter Discount",
                detail_to_save = detail_to_save,
                viewModel = viewModel,
                key = "Edit_Discount"
            )
            Spacer(modifier = Modifier.height(20.dp))

            Edit_List_View(
                invo = "Tax Amount",
                invoiceNumber = "Enter Tax Amount",
                detail_to_save = detail_to_save,
                viewModel = viewModel,
                key = "Edit_Tax_Rate"
            )
            Spacer(modifier = Modifier.height(20.dp))
            
            lastBlue(amount = viewModel.Edit_Cal_Amount())
            
            



        }

    }



}




@Composable
fun Edit_List_View(
    invo: String,
    invoiceNumber: String,
    detail_to_save: invoice_info_Details,
    viewModel: ViewModel1,
    key: String

){
    Text(text = "$invo ", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 5.dp))

    OutlinedTextField(value = viewModel.CheckValue_For_TextField(key), onValueChange = {
        viewModel.onDataChange(key,it.toString(),detail_to_save)
    },
        singleLine = true, modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
        ,
        placeholder = { Text(text = invoiceNumber)},
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = mytextColor,
            unfocusedTextColor = mytextColor,
            focusedBorderColor = Color.Gray,
            cursorColor = mytextColor,
            unfocusedPlaceholderColor = Color.Gray,
            focusedPlaceholderColor = Color.Transparent
        )
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar_two_action(
    navController: NavHostController,
    Title: String,
    nav_Icon: ImageVector?,
    action_Icon: ImageVector,
    onClick_Action : ()-> Unit,
    second_Action : ImageVector,
    onClick_Second_Action : ()-> Unit
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
            IconButton(onClick = {
                Log.i("shivam","delete click")
                onClick_Action() }) {
                Icon(imageVector = if (action_Icon == null){Icons.Outlined.Home} else {action_Icon}, contentDescription = "", tint = myWhite)
            }

            IconButton(onClick = { onClick_Second_Action() }) {
                Icon(imageVector = second_Action, contentDescription = "", tint = myWhite)
            }
        }
    )
}