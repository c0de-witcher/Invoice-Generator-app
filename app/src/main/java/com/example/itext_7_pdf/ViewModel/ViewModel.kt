package com.example.itext_7_pdf.ViewModel

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.example.itext_7_pdf.SharedPreffernce.invoice_info_Details
import com.example.itext_7_pdf.UIs.convertDate
import com.example.itext_7_pdf.UIs.itemList
import com.example.itext_7_pdf.UIs.removeSpaces
import java.util.Date

class ViewModel1: ViewModel() {
    var Invoice_Number = mutableStateOf("")
    var Creation_Date = mutableStateOf(convertDate(Date().time))
    var Due_Date = mutableStateOf(convertDate(Date().time))
    var Invoice_Title = mutableStateOf("")
    var Business_Name = mutableStateOf("")
    var Email_Address = mutableStateOf("")
    var Phone = mutableStateOf("")
    var Billing_Address_1 = mutableStateOf("")
    var Billing_Address_2 = mutableStateOf("")
    var Business_Website = mutableStateOf("")
    var Client_Name = mutableStateOf("")
    var Client_Email_Address = mutableStateOf("")
    var Client_Phone = mutableStateOf("")
    var Client_Billing_Address_1 = mutableStateOf("")
    var Client_Billing_Address_2 = mutableStateOf("")
    var Client_Shipping_Address_1 = mutableStateOf("")
    var Client_Shipping_Address_2 = mutableStateOf("")
    var Item_Name = mutableStateOf("")
    var Item_Price = mutableStateOf("0")
    var Item_Quantity = mutableStateOf("1")
    var Unit_of_Measure = mutableStateOf("")
    var Discount = mutableStateOf("0")
    var Tax_Rate = mutableStateOf("0")
    var Amount = mutableStateOf("0.00")
    var itemList1 = arrayListOf<itemList>()




    fun addItemInList( itemName : String, Qunantiy : String, price : String, total : String){
        itemList1.add(itemList(itemName,Qunantiy,price,total))
    }





    fun Cal_Amount(): String{

        CheckNull(Item_Price.value,Item_Quantity.value,Discount.value,Tax_Rate.value)

        return Amount.value
    }

    fun CheckNull(value: String, value1: String, value2: String, value3: String) {
        var v1 = if (value.isEmpty())"1" else value
        var v2  = if (value1.isEmpty())"1" else value1
        var v3  = if (value2.isEmpty())"0" else value2
        var v4  = if (value3.isEmpty())"0" else value3
        Amount.value = ( v1.toDouble()*v2.toDouble() - v3.toDouble()+ v4.toDouble()).toString()


    }





    fun onDataChange(invo: String,newData: String,detail_to_save: invoice_info_Details){
        Log.i("shivam "," date ${removeSpaces(invo)} and new data $newData and name ${Business_Name.value}")
        when(removeSpaces(invo)){
            "Business_Website" -> {Business_Website.value = newData}
            "Invoice_Number" -> {Invoice_Number.value= newData }
            "Business_Name" -> { Business_Name.value = newData}
            "Email_Address" -> {Email_Address.value = newData}
            "Phone" -> {Phone.value = newData}
            "Billing_Address_1" -> {Billing_Address_1.value = newData}
            "Billing_Address_2"-> {Billing_Address_2.value =newData}
            "Invoice_Title"-> {Invoice_Title.value = newData}
            "Client_Name"-> {Client_Name.value = newData}
            "Client_Email_Address"-> {Client_Email_Address.value =newData}
            "Client_Phone"-> {Client_Phone.value =newData}
            "Client_Billing_Address_1"-> {Client_Billing_Address_1.value =newData}
            "Client_Billing_Address_2"-> {Client_Billing_Address_2.value =newData}
            "Client_Shipping_Address_1"-> {Client_Shipping_Address_1.value =newData}
            "Client_Shipping_Address_2"-> {Client_Shipping_Address_2.value =newData}
            "Item_Name" -> {Item_Name.value = newData}
            "Item_Price" -> {Item_Price.value = newData
            Log.i("shivam","old ${Item_Price.value} and new $newData")}
            "Item_Quantity" -> {Item_Quantity.value = newData}
            "Unit_of_Measure" -> {Unit_of_Measure.value = newData}
            "Discount" -> {Discount.value = newData}
            "Tax_Amount" -> {Tax_Rate.value = newData}



        }
    }
    var re  = mutableStateOf("")
    var reInt = mutableStateOf<Int>(0)
    fun CheckValue_For_TextField(invo: String) : String{


        when(removeSpaces(invo)){
            "Business_Website" -> {re =  Business_Website }
            "Invoice_Number" -> {re =  Invoice_Number }
            "Creation_Date" -> {re =  Creation_Date }
            "Invoice_Title" -> {re = Invoice_Title }
            "Due_Date" -> {re =  Due_Date }
            "Business_Name" -> { re =  Business_Name }
            "Email_Address" -> {re =  Email_Address }
            "Phone" -> {re =  Phone }
            "Billing_Address_1" -> {re =  Billing_Address_1 }
            "Billing_Address_2"-> {re =  Billing_Address_2}
            "Creation_Date" -> { re = Creation_Date}
            "Due_Date" -> {re = Due_Date}
            "Client_Name"-> {re = Client_Name }
            "Client_Email_Address"-> {re = Client_Email_Address}
            "Client_Phone"-> {re = Client_Phone}
            "Client_Billing_Address_1"-> {re = Client_Billing_Address_1}
            "Client_Billing_Address_2"-> {re = Client_Billing_Address_2}
            "Client_Shipping_Address_1"-> {re = Client_Shipping_Address_1}
            "Client_Shipping_Address_2"-> {re = Client_Shipping_Address_2}
            "Item_Name" -> {re = Item_Name}
            "Item_Price" -> {re = Item_Price}
            "Item_Quantity" -> {re = Item_Quantity}
            "Unit_of_Measure" -> {re = Unit_of_Measure}
            "Discount" -> {re = Discount}
            "Tax_Amount" -> {re = Tax_Rate}



        }
        return re.value
    }


    @OptIn(ExperimentalMaterial3Api::class)
    fun On_date_Change(datePicker: DatePickerState, invo: String) : Boolean {
        var text : Boolean = true
        if (datePicker.selectedDateMillis != null){
            when(removeSpaces(invo)) {
                 "Creation_Date" -> { Creation_Date.value =  convertDate(datePicker.selectedDateMillis!!)}
                "Due_Date" -> { Due_Date.value =  convertDate(datePicker.selectedDateMillis!!)}
            }


            text = false

        }
        return text
    }


     //  data.value = newData
      /*  if (invo =="Invoice Number"){
            detail_to_save.Invoice_Number = data.value
        }
        else if (invo == "Invoice Title"){
            detail_to_save.Invoice_Title = data.value
        }*/

    }







