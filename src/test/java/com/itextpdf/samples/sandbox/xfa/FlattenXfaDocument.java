/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2020 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package com.itextpdf.samples.sandbox.xfa;

import com.itextpdf.tool.xml.xtra.xfa.MetaData;
import com.itextpdf.tool.xml.xtra.xfa.XFAFlattener;
import com.itextpdf.tool.xml.xtra.xfa.XFAFlattenerProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

public class FlattenXfaDocument {
    public static final String DEST = "./target/sandbox/xfa/flattened.pdf";

    public static final String XFA = "./src/test/resources/xfa/xfa.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new FlattenXfaDocument().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        MetaData metaData = new MetaData()
                .setAuthor("iText Samples")
                .setLanguage("EN")
                .setSubject("Showing off our flattening skills")
                .setTitle("Flattened XFA");

        XFAFlattenerProperties flattenerProperties = new XFAFlattenerProperties()
                .setPdfVersion(XFAFlattenerProperties.PDF_1_7)
                .createXmpMetaData()
                .setTagged()
                .setMetaData(metaData);

        List<String> javascriptEvents = Arrays.asList("click");
        XFAFlattener xfaFlattener = new XFAFlattener()
                .setExtraEventList(javascriptEvents)
                .setFlattenerProperties(flattenerProperties)
                .setViewMode(XFAFlattener.ViewMode.SCREEN);

        xfaFlattener.flatten(new FileInputStream(XFA), new FileOutputStream(dest));
    }
}
