/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2020 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
*/

/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/25808367/rotate-multiline-text-with-columntext-itextsharp
 */

package com.itextpdf.samples.sandbox.stamper;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;

public class AddRotatedTemplate {
    public static final String SRC = "./src/test/resources/pdfs/hello.pdf";
    public static final String DEST = "./target/sandbox/stamper/add_rotated_template.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new AddRotatedTemplate().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(dest));

        // Add content to the template without rotation
        PdfFormXObject formXObject = new PdfFormXObject(new Rectangle(80, 120));
        new Canvas(formXObject, pdfDoc)
                .add(new Paragraph("Some long text that needs to be distributed over several lines."));

        // Add template to the pdf document page applying rotation
        PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage());
        canvas.addXObject(formXObject, 36, 600);
        double angle = Math.PI / 4;
        canvas.addXObject(formXObject,
                (float) Math.cos(angle), -(float) Math.sin(angle),
                (float) Math.cos(angle), (float) Math.sin(angle),
                150, 600);

        pdfDoc.close();
    }
}
