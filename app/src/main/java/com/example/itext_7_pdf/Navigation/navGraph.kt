package com.example.itext_7_pdf.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.itext_7_pdf.SharedPreffernce.SharedPrefference
import com.example.itext_7_pdf.SharedPreffernce.invoice_info_Details
import com.example.itext_7_pdf.UIs.ClientInfo
import com.example.itext_7_pdf.UIs.HomePAge
import com.example.itext_7_pdf.UIs.InvoiceInfo
import com.example.itext_7_pdf.UIs.New_Item
import com.example.itext_7_pdf.UIs.PageLayout
import com.example.itext_7_pdf.UIs.businessInfo
import com.example.itext_7_pdf.UIs.itemDetail_View
import com.example.itext_7_pdf.ViewModel.ViewModel1


@Composable
fun nav(
    viewModel: ViewModel1,
    sharedPrefference: SharedPrefference,
    detail_to_save: invoice_info_Details
) {
    val context = LocalContext.current

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home_Screen") {
        composable(route = "Home_Screen"){
            HomePAge(context = context,navController,viewModel,sharedPrefference,detail_to_save)

        }

        composable(route = "create_new_invoice"){
            PageLayout(navController,viewModel,sharedPrefference,detail_to_save)

        }

        composable("Invoice_Info"){
            InvoiceInfo(navController,viewModel,sharedPrefference,detail_to_save)
        }

        composable("Business_Info"){
            businessInfo(navController,viewModel,sharedPrefference,detail_to_save)
        }

        composable("NewClient"){
            ClientInfo(navController,viewModel,sharedPrefference,detail_to_save)
        }

        composable("NewItem"){
            New_Item(navController,viewModel,sharedPrefference,detail_to_save)
        }

        composable("listItem/{index}", arguments = listOf(navArgument("index"){
            type = NavType.StringType
        }
        )){
            itemDetail_View(navController,viewModel,it.arguments?.getString("index"),detail_to_save)

        }
    }
}