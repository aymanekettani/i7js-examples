/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2020 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * This example was written by Bruno Lowagie in answer to the following questions:
 * http://stackoverflow.com/questions/24562448/the-table-width-must-be-greater-than-zero-exception-when-using-nested-tables
 * and
 * http://stackoverflow.com/questions/28444598/nested-table-stretches
 */
package com.itextpdf.samples.sandbox.tables;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.io.File;

public class NestedTables {
    public static final String DEST = "./target/sandbox/tables/nested_tables.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new NestedTables().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc, PageSize.A4.rotate());

        float[] columnWidths = {150, 40, 90, 51, 35, 25, 35, 35, 35, 32, 32, 33, 35, 60, 46, 26};
        Table table = new Table(columnWidths);
        table.setWidth(770F);

        buildNestedTables(table);

        doc.add(new Paragraph("Add table straight to another table"));
        doc.add(table);

        doc.close();
    }

    private static void buildNestedTables(Table outerTable) {
        Table innerTable1 = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
        Table innerTable2 = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();

        innerTable1.addCell("Cell 1");
        innerTable1.addCell("Cell 2");
        outerTable.addCell(innerTable1);

        innerTable2.addCell("Cell 3");
        innerTable2.addCell("Cell 4");
        outerTable.addCell(innerTable2);

        Cell cell = new Cell(1, 14);
        outerTable.addCell(cell);
    }
}
