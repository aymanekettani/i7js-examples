/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2020 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
*/

/**
 * This example was written in answer to the question:
 * http://stackoverflow.com/questions/33952183
 */

package com.itextpdf.samples.sandbox.stamper;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import java.io.File;

public class HighLightByAddingContent {
    public static final String DEST = "./target/sandbox/stamper/high_light_by_adding_content.pdf";
    public static final String SRC = "./src/test/resources/pdfs/hello.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new HighLightByAddingContent().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(dest));

        // The content, placed on a content stream before, will be rendered before the other content
        // and, therefore, could be understood as a background (bottom "layer")
        PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage().newContentStreamBefore(),
                pdfDoc.getFirstPage().getResources(), pdfDoc);

        canvas
                .saveState()
                .setFillColor(ColorConstants.YELLOW)
                .rectangle(36, 786, 66, 16)
                .fill()
                .restoreState();

        pdfDoc.close();
    }
}
