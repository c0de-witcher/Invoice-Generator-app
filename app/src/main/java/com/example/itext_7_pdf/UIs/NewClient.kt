package com.example.itext_7_pdf.UIs

import android.annotation.SuppressLint
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.itext_7_pdf.SharedPreffernce.SharedPrefference
import com.example.itext_7_pdf.SharedPreffernce.invoice_info_Details
import com.example.itext_7_pdf.ViewModel.ViewModel1
import com.example.itext_7_pdf.ui.theme.myWhite
import com.example.itext_7_pdf.ui.theme.mydellWhite

@Composable
fun ClientInfo(
    navController: NavHostController,
    viewModel: ViewModel1,
    sharedPrefference: SharedPrefference,
    detail_to_save: invoice_info_Details
) {
    Screen(
        navController = navController,
        Title = "New Client",
        nav_Icon = Icons.Filled.ArrowBack,
        action_Icon = Icons.Filled.Check,
        {},
        detail_to_save,
        viewModel,sharedPrefference
    )

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Screen(
    navController: NavHostController,
    Title: String,
    nav_Icon: ImageVector,
    action_Icon: ImageVector,
    onclick: () -> Unit,
    detail_to_save: invoice_info_Details,
    viewModel: ViewModel1,
    sharedPrefference: SharedPrefference
){
    Scaffold (
        topBar = { TopBar(navController = navController, Title = Title, nav_Icon = nav_Icon, action_Icon = action_Icon,onclick) },
        containerColor = mydellWhite,


    ){
        Content(viewModel,detail_to_save,sharedPrefference)


    }
}


@Composable
fun Content(
    viewModel: ViewModel1,
    detail_to_save: invoice_info_Details,
    sharedPrefference: SharedPrefference
) {
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
                invo = "Client Name",
                invoiceNumber = "Enter client name",
                detail_to_save = detail_to_save,
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(20.dp))

            invoiceNumber(
                invo = "Client Email Address",
                invoiceNumber = "Enter client email address",
                detail_to_save = detail_to_save,
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(20.dp))


            invoiceNumberInput(
                invo = "Client Phone",
                invoiceNumber = "Enter client phone number",
                detail_to_save = detail_to_save,
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(20.dp))


            invoiceNumber(
                invo = "Client Billing Address 1",
                invoiceNumber = "Enter address 1",
                detail_to_save = detail_to_save,
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(20.dp))

            invoiceNumber(
                invo = "Client Billing Address 2",
                invoiceNumber = "Enter address 2",
                detail_to_save = detail_to_save,
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(20.dp))

            invoiceNumber(
                invo = "Client Shipping Address 1",
                invoiceNumber = "Enter Shipping 1",
                detail_to_save = detail_to_save,
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(20.dp))

            invoiceNumber(
                invo = "Client Shipping Address 2",
                invoiceNumber = "Enter Shipping 2",
                detail_to_save = detail_to_save,
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(20.dp))
        }



    }
}

@Preview(showSystemUi = true)
@Composable
private fun view() {
   // Content(viewModel, detail_to_save, sharedPrefference)
}

