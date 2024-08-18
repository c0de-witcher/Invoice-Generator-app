package com.example.itext_7_pdf.LoadData

import android.content.Context
import java.io.File

fun getInvoice(context: Context) : List<File>? {
    val getInstance = File(context.getExternalFilesDir(null),"Invoices")
    return if(getInstance.exists()){
        getInstance.listFiles()?.toList()?: emptyList()
    } else {
        emptyList<File>()
    }
}