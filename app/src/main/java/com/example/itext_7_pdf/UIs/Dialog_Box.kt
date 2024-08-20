package com.example.itext_7_pdf.UIs

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.itext_7_pdf.ui.theme.myBlue

@Composable
fun Dialog_Box(title : String,check : Boolean): Boolean{


    var si = remember {
        mutableStateOf(false)
    }
    si.value = check

    if (si.value == true) {
        AlertDialog(onDismissRequest = { si.value = false}, confirmButton = {
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Save", color = myBlue, fontSize = 17.sp)
            }
            TextButton(onClick = { si.value = false}) {
                Text(text = "Cancel", color = Color.Gray, fontSize = 17.sp)
            }
        },
            title = { Text(text = title, color = myBlue, fontWeight = FontWeight.Bold, fontSize = 20.sp,)}
        )
    }

    return si.value



}