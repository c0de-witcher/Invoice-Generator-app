package com.example.itext_7_pdf.ItextToPdf

import android.content.Context
import android.text.style.LineBackgroundSpan.Standard
import androidx.compose.ui.graphics.Color
import com.itextpdf.io.font.constants.StandardFonts
import com.itextpdf.kernel.font.PdfFont
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.action.PdfAction
import com.itextpdf.kernel.pdf.canvas.draw.DashedLine
import com.itextpdf.layout.Document
import com.itextpdf.layout.Style
import com.itextpdf.layout.element.LineSeparator
import com.itextpdf.layout.element.Link
import com.itextpdf.layout.element.List
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.properties.ListNumberingType
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.TransparentColor
import java.io.File

fun invoiceLayout1(context: Context,directory : File){
    val outputfile = File(directory,"adityam.pdf")
    val write = PdfWriter(outputfile)
    val pdfDocument = com.itextpdf.kernel.pdf.PdfDocument(write)
    val document = Document(pdfDocument, PageSize.A4)
    pdfDocument.addNewPage()
    document.setMargins(0f,0f,0f,0f)

    // Paragraph
    val header = com.itextpdf.layout.element.Paragraph()
    header.add(Text("Hello World, How are you "))
        .setTextAlignment(TextAlignment.CENTER)
        .setFontSize(20f)
        .setBold()
        .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_BOLD))
    document.add(header)


    //add list
    val list = List()
    list.add("adityam srivastava")
    list.setListSymbol(ListNumberingType.GREEK_LOWER)   // bullets of the list
    list.addStyle(
        Style()
            .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLDOBLIQUE))   //to change the style of the font
    )
    document.add(list)


    // hyper link
    val link = Link(
        "Go to the Offical Page",
        PdfAction.createURI("www.google.com")
    ).setBold()
        .setFontSize(10f)
        .setUnderline()
    document.add(Paragraph().add(link))


    //line separator
    val lineSeparater = LineSeparator(DashedLine(1f))  // we also use SolidLIne and etc
    document.add(lineSeparater)


    //table
    val table = Table(3,false) // large TAble means table will occuupide the whole breadth
    table.addCell("Column 1").setTextAlignment(TextAlignment.CENTER)
    table.addCell("Column 2")
    table.addCell("Column 3")

    table.addCell("Row 1 Column 1")
    table.addCell("Row 1 Column 1")
    table.addCell("Row 1 Column 1")

    document.add(table)




    document.close()


}