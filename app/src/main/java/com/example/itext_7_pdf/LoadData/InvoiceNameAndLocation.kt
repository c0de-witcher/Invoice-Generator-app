package com.example.itext_7_pdf.LoadData

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.itext_7_pdf.R
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
fun LIstView(list: List<InvoiceNameAndLocation?>){
    LazyColumn(modifier = Modifier.fillMaxWidth().padding(top = 40.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        items(list){
            ListLayout(invoiceNameAndLocation = it)
        }
    }

}
@Composable
fun ListLayout(invoiceNameAndLocation: InvoiceNameAndLocation?) {
    Box(modifier = Modifier

        .padding(start = 20.dp, end = 20.dp, top = 2.dp, bottom = 5.dp)
        .shadow(3.dp, RoundedCornerShape(10.dp))
        .fillMaxWidth()
        .clip(
            RoundedCornerShape(10.dp)
        )
        .background(myWhite)
        .clickable(enabled = true, onClick = {Log.i("shivam ","hello")})
       , contentAlignment = Alignment.Center ){
       Row (modifier = Modifier.fillMaxWidth() , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
           Image(painter = painterResource(id = R.drawable.pdf_icon), contentDescription = "pdf Icon", modifier = Modifier.padding(start = 30.dp, end = 20.dp))
           if (invoiceNameAndLocation != null) {
               Text(text = invoiceNameAndLocation.FileName,Modifier.padding(20.dp))
           }
           if (invoiceNameAndLocation != null) {
               Text(text = invoiceNameAndLocation.Date,Modifier.padding(20.dp), maxLines = 1, overflow = TextOverflow.Ellipsis)
           }

       }

    }


}

@Preview(showSystemUi = true)
@Composable
private fun view() {
   //ListLayout(it)

}

