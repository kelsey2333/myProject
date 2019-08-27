package com.yaspeed.core.util;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class XmlToDocx {
    public  File outDocx(File documentFile, String docxTemplate, String toFilePath) throws ZipException, IOException {

        try {
            String fileName =docxTemplate;

            File docxFile = new File(fileName);
            ZipFile zipFile = new ZipFile(docxFile);
            Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();
            ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(toFilePath));
            int len=-1;
            byte[] buffer=new byte[1024];
            while(zipEntrys.hasMoreElements()) {
                ZipEntry next = zipEntrys.nextElement();
                InputStream is = zipFile.getInputStream(next);
                zipout.putNextEntry(new ZipEntry(next.toString()));
                if("word/document.xml".equals(next.toString())){
                    InputStream in = new FileInputStream(documentFile);
                    while((len = in.read(buffer))!=-1){
                        zipout.write(buffer,0,len);
                    }
                    in.close();
                }else {
                    while((len = is.read(buffer))!=-1){
                        zipout.write(buffer,0,len);
                    }
                    is.close();
                }
            }
            zipout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File file = new File(toFilePath);
        return file;
    }

}
