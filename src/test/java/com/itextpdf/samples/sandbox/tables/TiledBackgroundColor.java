/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2020 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/34177025
 */
package com.itextpdf.samples.sandbox.tables;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.PatternColor;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfPatternCanvas;
import com.itextpdf.kernel.pdf.colorspace.PdfPattern;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.io.File;

public class TiledBackgroundColor {
    public static final String DEST = "./target/sandbox/tables/tiled_background_color.pdf";

    public static final String IMG = "./src/test/resources/img/bulb.gif";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new TiledBackgroundColor().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        ImageData img = ImageDataFactory.create(IMG);
        Image image = new Image(img);

        PdfPattern.Tiling imgPattern = new PdfPattern.Tiling(image.getImageScaledWidth(),
                image.getImageScaledHeight());

        PdfPatternCanvas canvas = new PdfPatternCanvas(imgPattern, pdfDoc);
        canvas.addImage(img, 0, 0, false);

        Color color = new PatternColor(imgPattern);

        Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        table.addCell(new Cell().add(new Paragraph("Behold a cell with an image pattern:")));

        Cell cell = new Cell();
        cell.setHeight(60);
        cell.setBackgroundColor(color);
        table.addCell(cell);

        doc.add(table);

        doc.close();
    }
}
