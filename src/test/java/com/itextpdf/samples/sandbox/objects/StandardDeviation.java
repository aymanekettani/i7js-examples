/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2020 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * This code sample was written by Bruno Lowagie in answer to this question:
 * http://stackoverflow.com/questions/30876926/itext-std-deviation-symbol-%CF%83-not-printing
 */
package com.itextpdf.samples.sandbox.objects;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import java.io.File;
import java.io.IOException;

public class StandardDeviation {
    public static final String DEST = "./target/sandbox/objects/standard_deviation.pdf";

    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new StandardDeviation().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        doc.add(new Paragraph("The standard deviation symbol doesn't exist in Helvetica."));

        PdfFont symbolFont = PdfFontFactory.createFont(StandardFonts.SYMBOL);
        Paragraph p = new Paragraph("So we use the Symbol font: ");
        p.add(new Text("s").setFont(symbolFont));
        doc.add(p);

        doc.close();
    }
}
