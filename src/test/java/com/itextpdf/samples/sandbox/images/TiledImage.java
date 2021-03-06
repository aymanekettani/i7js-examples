/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2020 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * This code sample was written by Bruno Lowagie in answer to this question:
 * http://stackoverflow.com/questions/26859473/how-to-show-an-image-with-large-dimensions-across-multiple-pages-in-itextpdf
 */
package com.itextpdf.samples.sandbox.images;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import java.io.File;

public class TiledImage {
    public static final String DEST = "./target/sandbox/images/tiled_image.pdf";

    public static final String IMAGE = "./src/test/resources/img/bruno_ingeborg.jpg";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new TiledImage().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        ImageData image = ImageDataFactory.create(IMAGE);
        float width = image.getWidth();
        float height = image.getHeight();
        PageSize pageSize = new PageSize(width / 2, height / 2);
        pdfDoc.setDefaultPageSize(pageSize);

        PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
        canvas.addImage(image, width, 0, 0, height, 0, -height / 2, false);

        canvas = new PdfCanvas(pdfDoc.addNewPage());
        canvas.addImage(image, width, 0, 0, height, 0, 0, false);

        canvas = new PdfCanvas(pdfDoc.addNewPage());
        canvas.addImage(image, width, 0, 0, height, -width / 2, -height / 2, false);

        canvas = new PdfCanvas(pdfDoc.addNewPage());
        canvas.addImage(image, width, 0, 0, height, -width / 2, 0, false);

        pdfDoc.close();
    }
}
