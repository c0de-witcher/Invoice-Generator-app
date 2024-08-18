package com.example.itext_7_pdf.UIs

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.itext_7_pdf.SharedPreffernce.SharedPrefference
import com.example.itext_7_pdf.SharedPreffernce.invoice_info_Details
import com.example.itext_7_pdf.ViewModel.ViewModel1
import com.example.itext_7_pdf.ui.theme.myBlue
import com.example.itext_7_pdf.ui.theme.myWhite
import com.example.itext_7_pdf.ui.theme.mydellWhite
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun businessInfo(
    navController: NavHostController,
    viewModel: ViewModel1,
    sharedPrefference: SharedPrefference,
    detail_to_save: invoice_info_Details
) {
    Scaffold(
        topBar = {TopBar4(navController,sharedPrefference,detail_to_save)},
        containerColor = mydellWhite
    ) {
        LazyColumn {
            item{
                content2(detail_to_save,viewModel)
            }
        }




    }
}

@Composable
fun content2(detail_to_save: invoice_info_Details, viewModel: ViewModel1) {

        Card (modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp, start = 15.dp, end = 15.dp, bottom = 20.dp), colors = CardDefaults.cardColors(
            containerColor = myWhite
        ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 5.dp
            )
        ){
            Column (modifier = Modifier
                .padding(15.dp, top = 20.dp, end = 15.dp, bottom = 20.dp)
                ){
                invoiceNumber(
                    invo = "Business Name",
                    invoiceNumber = "Enter business name",
                    detail_to_save = detail_to_save,
                    viewModel = viewModel
                )
                Spacer(modifier = Modifier.height(20.dp))

                invoiceNumber(
                    invo = "Email Address",
                    invoiceNumber = "Enter business email address",
                    detail_to_save = detail_to_save,
                    viewModel = viewModel
                )
                Spacer(modifier = Modifier.height(20.dp))

                invoiceNumberInput(
                    invo = "Phone",
                    invoiceNumber = "Enter business phone number",
                    detail_to_save = detail_to_save,
                    viewModel = viewModel
                )
                Spacer(modifier = Modifier.height(20.dp))

                invoiceNumber(
                    invo = "Billing Address 1",
                    invoiceNumber = "Enter Address line 1",
                    detail_to_save = detail_to_save,
                    viewModel = viewModel
                )
                invoiceNumber(
                    invo = "Billing Address 2",
                    invoiceNumber = "Enter Address line 2",
                    detail_to_save = detail_to_save,
                    viewModel = viewModel
                )
                Spacer(modifier = Modifier.height(20.dp))

                invoiceNumber(
                    invo = "Business Website",
                    invoiceNumber = "Enter business website",
                    detail_to_save = detail_to_save,
                    viewModel = viewModel
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar4(
    navController: NavHostController,
    sharedPrefference: SharedPrefference,
    saveDetails: invoice_info_Details,

    ) {
    val state = rememberCoroutineScope()

    TopAppBar(title = { Text(text = "Business Info", fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 10.dp), fontSize = 20.sp) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = myBlue,
            titleContentColor = myWhite,
            navigationIconContentColor = myWhite
        ),
        navigationIcon = {
            IconButton(onClick = { navController.navigate("create_new_invoice")}) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "BAck")
            }
        },
        actions = {
            IconButton(onClick = {
                state.launch {
                    sharedPrefference.setData("Business_Name",saveDetails.Business_Name)
                    sharedPrefference.setData("Email_Address",saveDetails.Email_Address)
                    sharedPrefference.setData("Phone",saveDetails.Phone)
                    sharedPrefference.setData("Billing_Address_1",saveDetails.Billing_Address_1)
                    sharedPrefference.setData("Billing_Address_2",saveDetails.Billing_Address_2)
                    sharedPrefference.setData("Business_Website",saveDetails.Business_Website)
                    navController.navigate("create_new_invoice")

                }

            }) {
                Icon(imageVector = Icons.Filled.Check, contentDescription = "Save", tint = myWhite)
            }
        }
    )
}