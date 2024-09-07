package com.example.itext_7_pdf.UIs

import android.annotation.SuppressLint
import android.content.res.Resources
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.itext_7_pdf.ViewModel.ViewModel1
import com.example.itext_7_pdf.ui.theme.myWhite
import com.example.itext_7_pdf.ui.theme.mydellWhite
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState
import java.io.File
import java.net.URI

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun pdf_preview(viewModel1: ViewModel1, navController: NavHostController, ){
    //val height = Resources.getSystem().displayMetrics.heightPixels / Resources.getSystem().displayMetrics.density
   Scaffold(
       topBar = {
           TopBar(
               navController = navController,
               Title = "Preview",
               nav_Icon = Icons.Filled.ArrowBack,
               action_Icon = Icons.Outlined.Delete
           ) {
              val absoultPath = File(viewModel1.pdf_address)

               if (absoultPath.exists()){
                   if (absoultPath.delete()){
                       navController.navigate("Home_Screen")
                   }
               }


           }
       }
   ) {
       val file = File(viewModel1.pdf_address)
       val uri = Uri.fromFile(file)
       val ste = rememberVerticalPdfReaderState(resource = ResourceType.Local(uri))
       Surface(modifier = Modifier.fillMaxSize(), color = mydellWhite) {


           VerticalPDFReader(
               state = ste,
               modifier = Modifier
                   .background(color = mydellWhite)
                   .padding(top = 70.dp)
                   .shadow(3.dp)


           )

       }


   }
}