/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2020 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
*/

/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/24217657/set-inherit-zoomaction-property-to-bookmark-in-the-pdf-file
 */

package com.itextpdf.samples.sandbox.stamper;

import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfNumber;
import com.itextpdf.kernel.pdf.PdfOutline;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.File;
import java.util.List;

public class ChangeBookmarks {
    public static final String DEST = "./target/sandbox/stamper/change_bookmarks.pdf";
    public static final String SRC = "./src/test/resources/pdfs/bookmarks.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new ChangeBookmarks().manipulatePdf(DEST);
    }

    public void changeList(List<PdfOutline> list) {
        for (PdfOutline entry : list) {
            PdfArray array = ((PdfArray) entry.getContent().get(PdfName.Dest));
            for (int i = 0; i < array.size(); i++) {
                if (PdfName.Fit.equals(array.get(i))) {
                    array.set(i, PdfName.FitV);
                    array.add(new PdfNumber(60));
                }
            }
        }
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(dest));
        PdfOutline outlines = pdfDoc.getOutlines(false);
        List<PdfOutline> children = outlines.getAllChildren().get(0).getAllChildren();
        changeList(children);
        pdfDoc.close();
    }
}
