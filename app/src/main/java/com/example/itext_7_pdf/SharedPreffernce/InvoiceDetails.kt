package com.example.itext_7_pdf.SharedPreffernce

data class InvoiceDetails(
    val invoiceNumber: String,
    val createdDate: String,
    val dueDate: String,
    val totalInvoice: String,
    val totalMoney: String
)
