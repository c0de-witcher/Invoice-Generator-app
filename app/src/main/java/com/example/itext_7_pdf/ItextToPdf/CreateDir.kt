package com.example.itext_7_pdf.ItextToPdf

import android.content.Context
import java.io.File

fun CreateDir(context: Context,dirName : String = "Invoices"): File?{
    val getInstance = context.getExternalFilesDir(null)
    val newDir = File(getInstance,dirName)
    return if(newDir.exists() || newDir.mkdir()) {
        newDir
    }
    else{
        null
    }

}