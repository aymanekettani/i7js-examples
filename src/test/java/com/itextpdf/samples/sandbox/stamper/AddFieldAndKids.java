/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2020 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
*/

/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/28418555/remove-page-reference-from-annotation0
 */

package com.itextpdf.samples.sandbox.stamper;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.File;

public class AddFieldAndKids {
    public static final String DEST = "./target/sandbox/stamper/add_field_and_kids.pdf";
    public static final String SRC = "./src/test/resources/pdfs/hello.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new AddFieldAndKids().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(dest));

        PdfFormField personal = PdfFormField.createEmptyField(pdfDoc);
        personal.setFieldName("personal");
        PdfTextFormField name =
                PdfFormField.createText(pdfDoc, new Rectangle(36, 760, 108, 30),
                        "name", "");
        personal.addKid(name);
        PdfTextFormField password =
                PdfFormField.createText(pdfDoc, new Rectangle(150, 760, 300, 30),
                        "password", "");
        personal.addKid(password);

        PdfAcroForm.getAcroForm(pdfDoc, true).addField(personal, pdfDoc.getFirstPage());
        pdfDoc.close();
    }
}
