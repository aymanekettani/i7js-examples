/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2020 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/30843163/how-to-add-inline-images-in-itext-when-using-columntext-in-text-mode
 */
package com.itextpdf.samples.sandbox.columntext;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import java.io.File;

public class ColumnTextChunkImage {
    public static final String DEST = "./target/sandbox/columntext/column_text_chunk_image.pdf";

    public static final String DOG = "./src/test/resources/img/dog.bmp";
    public static final String FOX = "./src/test/resources/img/fox.bmp";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new ColumnTextChunkImage().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        Image dog = new Image(ImageDataFactory.create(DOG));
        Image fox = new Image(ImageDataFactory.create(FOX));
        Paragraph p = new Paragraph("quick brown fox jumps over the lazy dog.");
        p.add("Or, to say it in a more colorful way: quick brown ");
        p.add(fox);
        p.add(" jumps over the lazy ");
        p.add(dog);
        p.add(".");

        p.setWidth(350);
        doc.showTextAligned(p, 50, 650, TextAlignment.LEFT);

        doc.close();
    }
}
