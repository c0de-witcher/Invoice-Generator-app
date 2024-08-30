package com.example.itext_7_pdf.LoadData

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.itext_7_pdf.R
import com.example.itext_7_pdf.UIs.pdf_preview
import com.example.itext_7_pdf.ViewModel.ViewModel1
import com.example.itext_7_pdf.ui.theme.myWhite
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class InvoiceNameAndLocation(
    val FileName :String,
    val Address :String,
    val Date : String
)

fun MapAddressToList(file : List<File>?) : List<InvoiceNameAndLocation?>{
    val dateFormat = SimpleDateFormat("yyyy/MM/dd ", Locale.getDefault())
    if (file != null) {


        return file.map {
            val lastModified = it.lastModified()
            val formatedDate = dateFormat.format(Date(lastModified))
            it?.let { it1 -> InvoiceNameAndLocation(it1.name,it1.absolutePath,formatedDate) }
        }
    }
    return emptyList()

}

@Composable
fun LIstView(
    list: List<InvoiceNameAndLocation?>,
    viewModel1: ViewModel1,
    navController: NavHostController
){
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 40.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        items(list){
            ListLayout(invoiceNameAndLocation = it,viewModel1,navController)
        }
    }

}
@SuppressLint("UnrememberedMutableState")
@Composable
fun ListLayout(
    invoiceNameAndLocation: InvoiceNameAndLocation?,
    viewModel1: ViewModel1,
    navController: NavHostController
) {


    Button(onClick = {
        if (invoiceNameAndLocation != null) {
            viewModel1.pdf_address = invoiceNameAndLocation.Address
            navController.navigate("pdf_preview")
        }


    },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 2.dp, bottom = 5.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = myWhite,

        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 3.dp
        ),
        contentPadding = PaddingValues(0.dp)

    ) {
        Row (modifier = Modifier.fillMaxWidth() , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
            Image(painter = painterResource(id = R.drawable.pdf_icon), contentDescription = "pdf Icon", modifier = Modifier.padding(start = 30.dp, end = 20.dp))
            if (invoiceNameAndLocation != null) {
                Text(text = invoiceNameAndLocation.FileName,Modifier.padding(20.dp), color = Color.Black)
            }
            if (invoiceNameAndLocation != null) {
                Text(text = invoiceNameAndLocation.Date,Modifier.padding(20.dp), maxLines = 1, overflow = TextOverflow.Ellipsis, color = Color.Black)
            }

        }

    }
    





}


