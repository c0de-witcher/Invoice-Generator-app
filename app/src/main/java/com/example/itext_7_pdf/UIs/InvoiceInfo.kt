package com.example.itext_7_pdf.UIs

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.itext_7_pdf.SharedPreffernce.SharedPrefference
import com.example.itext_7_pdf.SharedPreffernce.invoice_info_Details
import com.example.itext_7_pdf.ViewModel.ViewModel1
import com.example.itext_7_pdf.ui.theme.myBlue
import com.example.itext_7_pdf.ui.theme.myGrey
import com.example.itext_7_pdf.ui.theme.myWhite
import com.example.itext_7_pdf.ui.theme.mydellWhite
import com.example.itext_7_pdf.ui.theme.mytextColor
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date




@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InvoiceInfo(
    navController: NavHostController,
    viewModel: ViewModel1,
    sharedPrefference: SharedPrefference,
    detail_to_save: invoice_info_Details
) {





    Scaffold (
        topBar = { TopBar3(navController = navController,sharedPrefference,detail_to_save)},
        containerColor = mydellWhite
    ){

        detailcontent(sharedPrefference,detail_to_save,viewModel)

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun detailcontent(
    sharedPrefference: SharedPrefference,
    detail_to_save: invoice_info_Details,
    viewModel: ViewModel1,

    ) {
    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(top = 80.dp, start = 15.dp, end = 15.dp, bottom = 20.dp), colors = CardDefaults.cardColors(
        containerColor = myWhite
    ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        )
    ){
        Column (modifier = Modifier.padding(15.dp, top = 20.dp, end = 15.dp, bottom = 20.dp)){

            invoiceNumber("Invoice Number","INV00002",detail_to_save,viewModel)
            Spacer(modifier = Modifier.height(20.dp))
            DatePicker(invo = "Creation Date",detail_to_save,viewModel)
            Spacer(modifier = Modifier.height(20.dp))
            DatePicker(invo = "Due Date",detail_to_save,viewModel)
            Spacer(modifier = Modifier.height(20.dp))
            invoiceNumber("Invoice Title", "Invoice", detail_to_save, viewModel,)
            Spacer(modifier = Modifier.height(20.dp))



        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(invo :String,detail_to_save: invoice_info_Details,viewModel: ViewModel1){
    var text by remember {
        mutableStateOf(false)
    }

    var datePicker = rememberDatePickerState()

    Text(text = "$invo *", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 5.dp))
    OutlinedButton(onClick = { text = true },
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = mydellWhite
        ),
        contentPadding = PaddingValues(0.dp)

       ) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, start = 20.dp, end = 20.dp, bottom = 15.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Text(text = viewModel.CheckValue_For_TextField(invo), color = mytextColor)
            Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Date", tint = myGrey)
        }

    }
    if (text){
       DatePickerDialog(onDismissRequest = { text = false },

           confirmButton = { TextButton(onClick = {
               text = viewModel.On_date_Change(datePicker,invo)
           }) {
           Text(text = "OK", fontWeight = FontWeight.Bold, color = myBlue, fontSize = 18.sp)

           }

                           },

          dismissButton = {
              TextButton(onClick = { text = false }) {
                  Text(text = "CANCEL", fontWeight = FontWeight.Bold, color = myBlue, fontSize = 18.sp)
              }
          } ,
           modifier = Modifier.padding(start = 30.dp, end = 30.dp),
           colors = DatePickerDefaults.colors(
               containerColor = myWhite,
           )) {
           DatePicker(state = datePicker,
               colors = DatePickerDefaults.colors(
                   todayDateBorderColor = myBlue,
                   selectedDayContainerColor = myBlue,
                   selectedYearContainerColor = myBlue,
                   containerColor = myWhite
               ))
       }
    }

}

fun convertDate(date : Long): String{
    val newDate = Date(date)
    val convert = SimpleDateFormat.getDateInstance()
    return convert.format(newDate)
}

@Composable
fun invoiceNumber(
    invo: String,
    invoiceNumber: String,
    detail_to_save: invoice_info_Details,
    viewModel: ViewModel1
){
    Text(text = "$invo ", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 5.dp))

    OutlinedTextField(value = viewModel.CheckValue_For_TextField(invo), onValueChange = {
       viewModel.onDataChange(invo,it,detail_to_save)
        Log.i("shivam",viewModel.Invoice_Title.value)
    },
        singleLine = true, modifier = Modifier
            // .border(1.dp, myGrey, RoundedCornerShape(5.dp))
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


@Composable
fun invoiceNumberInput(
    invo: String,
    invoiceNumber: String,
    detail_to_save: invoice_info_Details,
    viewModel: ViewModel1
){
    Text(text = "$invo ", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 5.dp))

    OutlinedTextField(value = viewModel.CheckValue_For_TextField(invo),
        onValueChange = {
        viewModel.onDataChange(invo,it.toString(),detail_to_save)
        Log.i("shivam",viewModel.Invoice_Title.value)
    },
        singleLine = true, modifier = Modifier
            // .border(1.dp, myGrey, RoundedCornerShape(5.dp))
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
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )

}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar3(
    navController: NavHostController,
    sharedPrefference: SharedPrefference,
    saveDetails: invoice_info_Details,

    ) {
    val state = rememberCoroutineScope()

    TopAppBar(title = { Text(text = "Invoice Info", fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 10.dp), fontSize = 20.sp) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = myBlue,
            titleContentColor = myWhite,
            navigationIconContentColor = myWhite
        ),
        navigationIcon = {
            IconButton(onClick = {

                navController.navigate("create_new_invoice")
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "BAck")
            }
        },
        actions = {
            IconButton(onClick = {
                state.launch {
                    sharedPrefference.setData("Invoice_Number",saveDetails.Invoice_Number)
                    sharedPrefference.setData("Creation_Date",saveDetails.Creation_Date)
                    sharedPrefference.setData("Due_Date",saveDetails.Due_Date)
                    sharedPrefference.setData("Invoice_Title",saveDetails.Invoice_Title)
                    navController.navigate("create_new_invoice")

                }

            }) {
                Icon(imageVector = Icons.Filled.Check, contentDescription = "Save", tint = myWhite)
            }
        }
    )
}


fun removeSpaces(input : String ) : String{
    return input.replace(" ","_")
}






/*state.launch {
    sharedPrefference.setData(removeSpaces(invo),date)
    // Log.i("shivam",sharedPrefference.getData("Creation_Date").firstOrNull().toString())
}*/
// date =
