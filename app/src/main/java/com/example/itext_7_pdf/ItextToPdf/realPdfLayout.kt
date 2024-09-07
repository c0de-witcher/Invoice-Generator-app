package com.example.itext_7_pdf.ItextToPdf

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.itext_7_pdf.SharedPreffernce.SharedPrefference
import com.example.itext_7_pdf.ViewModel.ViewModel1
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.action.PdfAction
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine
import com.itextpdf.layout.Document
import com.itextpdf.layout.Style
import com.itextpdf.layout.borders.SolidBorder
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.LineSeparator
import com.itextpdf.layout.element.Link
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.properties.HorizontalAlignment
import com.itextpdf.layout.properties.Leading
import com.itextpdf.layout.properties.Property
import com.itextpdf.layout.properties.TextAlignment
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun Desgin1(  itemList: ArrayList<itemlist>, viewModel1: ViewModel1){

    val context  = LocalContext.current


    val fileDir = File(context.getExternalFilesDir(null),"Invoices")




    val sharedPrefference = SharedPrefference(context)
    val invoice_num = sharedPrefference.getData("invoice")
    val date = LocalDate.now()
    val date_format = DateTimeFormatter.ofPattern("EEE YYYY/MM/DD")
    val formateDate = date.format(date_format)





    val file = File(fileDir,"${viewModel1.File_Name.value}.pdf")
    val writer = PdfWriter(file)
    val document = PdfDocument(writer)
    val doc = Document(document, PageSize(650f,700f)).apply {
        setMargins(50f,13f,13f,13f)
        setProperty(
            Property.LEADING,
            Leading(Leading.MULTIPLIED,1f)
        )
        setWordSpacing(0f)

    }
    document.addNewPage()

    //Invoice Number
    val InvoiceNumber = com.itextpdf.layout.element.Paragraph()
    InvoiceNumber.add(Text("INVOICE #$invoice_num"))
        .setFontSize(32f)

    //Date
    val Date = toLightText(formateDate)


    //Pay Button
    var sum : Int = 0
    itemList.forEach{
        sum = sum + it.SubTotal.toInt()
    }
    val patLink = Link("Pay $sum ", PdfAction.createURI("www.google.com"))
        .setTextAlignment(TextAlignment.RIGHT)
        .setFontColor(DeviceRgb.WHITE)
    val Button = com.itextpdf.layout.element.Paragraph().add(patLink)
        .setBackgroundColor(DeviceRgb(0,92,230))
        .setPadding(12f)
        .setHorizontalAlignment(HorizontalAlignment.RIGHT)
        .setTextAlignment(TextAlignment.CENTER)


    //top section table
    val headTable = Table(2)
        .setMarginRight(15f)
        .setMarginLeft(15f)
        .setWidth(650f - 30f -26f)
    headTable.addCell(NoBorder(InvoiceNumber))
    headTable.addCell(NoBorder(Button))
    headTable.addCell(NoBorder(Date))


    doc.add(headTable)

    //head line seprarter
    val headSeparater = LineSeparator(SolidLine(1f).apply {
        color = DeviceRgb(204,204,204)
    })
        .setMarginTop(20f)

    doc.add(headSeparater)


    //pepole info
    val from  = toLightText("From")
    val to = toLightText("To").setTextAlignment(TextAlignment.RIGHT)
    val senderName = com.itextpdf.layout.element.Paragraph(viewModel1.Business_Name.value).setMarginRight(20f)
    val CustomerName = com.itextpdf.layout.element.Paragraph(viewModel1.Client_Name.value).setTextAlignment(TextAlignment.RIGHT).setMarginLeft(20f)
    val SenderAddress = toLightText(viewModel1.Billing_Address_1.value).setMarginRight(20f)
    val CustomerAddress = toLightText(viewModel1.Client_Shipping_Address_1.value).setTextAlignment(TextAlignment.RIGHT).setMarginLeft(20f)

    //pepole details table
    val t2 = Table(2)
        .setWidth(650f-26f-50f)
        .setMarginTop(20f)
        .setMarginLeft(20f)
    t2.addCell(NoBorder(from))
    t2.addCell(NoBorder(to))
    t2.addCell(NoBorder(senderName))
    t2.addCell(NoBorder(CustomerName))
    t2.addCell(NoBorder(SenderAddress))
    t2.addCell(NoBorder(CustomerAddress))

    doc.add(t2)


    //Item Table
    val Sno = boldTextParagraph("S.no").setTextAlignment(TextAlignment.LEFT)
    val Description = boldTextParagraph("Description").setTextAlignment(TextAlignment.CENTER)
    val Rate = boldTextParagraph("Rate").setTextAlignment(TextAlignment.CENTER)
    val Qunatity = boldTextParagraph("Quantity").setTextAlignment(TextAlignment.CENTER)
    val subtotal = boldTextParagraph("Sub Total").setTextAlignment(TextAlignment.RIGHT)

    val ColumnWidth = floatArrayOf(20f,250f,100f,100f,100f)

    val t3 = Table(5,true)
        .setMarginRight(30f)
        .setMarginLeft(30f)
        .setMarginTop(30f)


    t3.addCell(ProductTableCell(Sno))
    t3.addCell(ProductTableCell(Description))
    t3.addCell(ProductTableCell(Rate))
    t3.addCell(ProductTableCell(Qunatity))
    t3.addCell(ProductTableCell(subtotal))
    doc.add(t3)



    //doc.add(LineSeparaterr())


    itemList.forEachIndexed { index, itemlist ->

        t3.addCell(ProductTableCell(Paragraph((index+1).toString()).setTextAlignment(TextAlignment.LEFT)))
        t3.addCell(ProductTableCell(Paragraph(itemlist.Description).setTextAlignment(TextAlignment.CENTER)))
        t3.addCell(ProductTableCell(Paragraph(itemlist.Rate).setTextAlignment(TextAlignment.CENTER)))
        t3.addCell(ProductTableCell(Paragraph(itemlist.Quantity).setTextAlignment(TextAlignment.CENTER)))
        t3.addCell(ProductTableCell(Paragraph((itemlist.Rate.toInt()*itemlist.Quantity.toInt()).toString()).setTextAlignment(TextAlignment.RIGHT)))
        doc.add(t3)


    }
    doc.add(LineSeparaterr())

    val GrandTotal = toLightText("Grand Total").setTextAlignment(TextAlignment.LEFT).setMarginRight(20f)
    val Sum = NoBorder(boldTextParagraph("$ $sum").setTextAlignment(TextAlignment.RIGHT))

    // Grand total Table
    val t4 = Table(2)
        .setMarginRight(30f)
        .setMarginTop(10f)
        .setHorizontalAlignment(HorizontalAlignment.RIGHT)
    t4.addCell(NoBorder(GrandTotal))
    t4.addCell(Sum)
    doc.add(t4)









    doc.close()
}
fun toLightText(Text : String) : com.itextpdf.layout.element.Paragraph{
    val lightTextStyle = com.itextpdf.layout.element.Paragraph(Text).addStyle(
        Style().apply {
            setFontColor(DeviceRgb(166,166,166))
        }
    )
    return lightTextStyle
}

fun NoBorder(paragraph : com.itextpdf.layout.element.Paragraph) : Cell{
    return Cell().add(paragraph).setBorder(null)
}

fun boldTextParagraph(text : String): com.itextpdf.layout.element.Paragraph{
    return com.itextpdf.layout.element.Paragraph(text).setBold().setFontSize(13f)
}

fun ProductTableCell(text : com.itextpdf.layout.element.Paragraph) : Cell{
    return Cell().add(text)
        .setBorder(null)
        .setBorderBottom(SolidBorder(DeviceRgb(204,204,204),1f))
        .setPaddingTop(15f)
        .setPaddingBottom(15f)
}

fun LineSeparaterr() : LineSeparator{
    val lineSeparator = LineSeparator(SolidLine(1f).apply {
        color = DeviceRgb(204,204,204)
    })
        .setMarginLeft(30f)
        .setMarginRight(30f)
        .setMarginTop(10f)
        .setMarginBottom(10f)
    return lineSeparator
}