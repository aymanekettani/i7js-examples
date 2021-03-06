/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2020 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/33873180/itext-pdf-add-text-in-absolute-position-on-top-of-the-1st-page
 */
package com.itextpdf.samples.sandbox.columntext;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;

public class WriteOnFirstPage {
    public static final String DEST = "./target/sandbox/columntext/write_on_first_page.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new WriteOnFirstPage().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);
        PdfFormXObject template = new PdfFormXObject(new Rectangle(0, 0, 523, 50));
        Canvas templateCanvas = new Canvas(template, pdfDoc);

        // In order to add a formXObject to the document, one can wrap it with an image
        doc.add(new Image(template));
        for (int i = 0; i < 100; i++) {
            doc.add(new Paragraph("test"));
        }

        String textLine = String.format("There are %s pages in this document", pdfDoc.getNumberOfPages());
        templateCanvas.add(new Paragraph(textLine));
        templateCanvas.close();

        doc.close();
    }
}
