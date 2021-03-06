/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2020 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * This example was written by Bruno Lowagie in answer to the following question:
 * https://www.linkedin.com/groups/Script-Change-Author-Name-Comments-159987.S.5984062085800144899
 */
package com.itextpdf.samples.sandbox.parse;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ParseCzech {
    public static final String DEST = "./target/txt/czech.txt";

    public static final String SRC = "./src/test/resources/pdfs/czech.pdf";

    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new ParseCzech().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC));

        // Create a text extraction renderer
        LocationTextExtractionStrategy strategy = new LocationTextExtractionStrategy();

        // Note: if you want to re-use the PdfCanvasProcessor, you must call PdfCanvasProcessor.reset()
        PdfCanvasProcessor parser = new PdfCanvasProcessor(strategy);
        parser.processPageContent(pdfDoc.getFirstPage());

        byte[] content = strategy.getResultantText().getBytes("UTF-8");
        try (FileOutputStream stream = new FileOutputStream(dest)) {
            stream.write(content);
        }

        pdfDoc.close();
    }
}
