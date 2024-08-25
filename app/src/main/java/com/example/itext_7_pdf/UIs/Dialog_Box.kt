package com.example.itext_7_pdf.UIs

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itext_7_pdf.SharedPreffernce.invoice_info_Details
import com.example.itext_7_pdf.ViewModel.ViewModel1
import com.example.itext_7_pdf.ui.theme.myBlue
import com.example.itext_7_pdf.ui.theme.myWhite
import com.example.itext_7_pdf.ui.theme.mytextColor

@SuppressLint("UnrememberedMutableState")
@Composable
fun Dialog_Box(
    title: String,
    viewModel: ViewModel1,
    detail_to_save: invoice_info_Details,
    invo1: String,
    Hint1: String,
    switchTwo: Boolean,
    invo2: String,
    Hint2: String,
    s : String

) {
    val check = remember {
        mutableStateOf<Boolean>(false)
    }
    var key :String = ""
    when(s){
        "D" -> {check.value = viewModel.checkDiscount.value
        key = "D"}
        "T" -> {check.value = viewModel.checkTax.value
            key = "D"}
        "S" -> {check.value = viewModel.checkShipping.value
            key = "D"}
    }




        if (check.value){
            AlertDialog(onDismissRequest = {  when(s){
                "D" -> {viewModel.checkDiscount.value = false}
                "T" -> {viewModel.checkTax.value = false}
                "S" -> {viewModel.checkShipping.value = false}
            }
                check.value = false}, confirmButton = {

                TextButton(onClick = {
                    when(s){
                        "D" -> {viewModel.checkDiscount.value = false}
                        "T" -> {viewModel.checkTax.value = false}
                        "S" -> {viewModel.checkShipping.value = false}
                    }

                    check.value = false }) {
                    Text(text = "Cancel", color = Color.Gray, fontSize = 17.sp)
                }


                TextButton(onClick = {  }) {
                    Text(text = "Save", color = myBlue, fontSize = 17.sp)
                }

            },
                title = { Text(text = title, color = myBlue, fontWeight = FontWeight.Bold, fontSize = 20.sp,)},
                shape = RoundedCornerShape(10.dp),
                containerColor = myWhite,
                text = {
                    Column {
                        invoiceNumber2(
                            invo = invo1,
                            invoiceNumber = Hint1,
                            detail_to_save = detail_to_save,
                            viewModel = viewModel,
                            s
                        )

                        if (switchTwo){
                            invoiceNumber2(
                                invo = invo2,
                                invoiceNumber = Hint2,
                                detail_to_save = detail_to_save,
                                viewModel = viewModel,
                                "$s 2"
                            )
                        }




                    }

                },
                modifier = Modifier.wrapContentHeight()
            )
        }






}

@Composable
fun invoiceNumber2(
    invo: String,
    invoiceNumber: String,
    detail_to_save: invoice_info_Details,
    viewModel: ViewModel1,
    key : String
){
    Column {
        Text(text = "$invo ", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 5.dp))

        OutlinedTextField(value = viewModel.CheckValue_For_TextField(key), onValueChange = {
            viewModel.onDataChange(key,it,detail_to_save)
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
}


