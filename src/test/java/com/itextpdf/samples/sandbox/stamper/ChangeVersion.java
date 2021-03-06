/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2020 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
*/

/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/23083220/how-to-set-pdf-version-using-itextsharp
 */

package com.itextpdf.samples.sandbox.stamper;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfVersion;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;

import java.io.File;

public class ChangeVersion {
    public static final String DEST = "./target/sandbox/stamper/change_version.pdf";
    public static final String SRC = "./src/test/resources/pdfs/OCR.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new ChangeVersion().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC),

                // Please note that the default PdfVersion value is PDF 1.7
                new PdfWriter(dest, new WriterProperties().setPdfVersion(PdfVersion.PDF_1_5)));

        pdfDoc.close();
    }
}
