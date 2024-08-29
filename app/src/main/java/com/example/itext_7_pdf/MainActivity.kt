package com.example.itext_7_pdf

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModelProvider
import com.example.itext_7_pdf.ItextToPdf.itemlist
import com.example.itext_7_pdf.Navigation.nav
import com.example.itext_7_pdf.SharedPreffernce.SharedPrefference
import com.example.itext_7_pdf.SharedPreffernce.invoice_info_Details
import com.example.itext_7_pdf.UIs.detailcontent
import com.example.itext_7_pdf.ViewModel.ViewModel1
import com.example.itext_7_pdf.ui.theme.myBlue
import com.example.itext_7_pdf.ui.theme.myWhite
import com.rizzi.bouquet.VerticalPDFReader
import java.io.File

class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.navigationBarColor = myWhite.toArgb()
        window.statusBarColor = myBlue.toArgb()
      val fileDir = File(this.getExternalFilesDir(null),"Invoices")

        val itemList = arrayListOf<itemlist>()
        itemList.add(itemlist("Boat Rockerz 510","1899","2","3798"))
        itemList.add(itemlist("Acer Nitro Gen 12","60000","1","60000"))
        itemList.add(itemlist("Demon World Manga ","500","2","1000"))


        val viewModel = ViewModelProvider(this)[ViewModel1::class.java]
        val sharedPrefference = SharedPrefference(this)
        var detail_to_save = invoice_info_Details()



        setContent {
            //invoiceLayout1(this,fileDir!!)
            //Desgin1(this,fileDir!!,itemList,"shivam")
           // HomePAge(context = this)
           // CreateDir(this)
          //  PageLayout(navController)
          nav(viewModel,sharedPrefference,detail_to_save)
           //detailcontent(sharedPrefference)
           /* VerticalPDFReader(
                state = viewModel.pdfVerticallReaderState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Gray)
            )*/




        }
    }
}


