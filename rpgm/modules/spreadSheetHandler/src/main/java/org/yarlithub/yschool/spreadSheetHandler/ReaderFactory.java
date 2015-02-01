package org.yarlithub.yschool.spreadSheetHandler;

import org.apache.commons.io.FilenameUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Jay Krish
 * Date: 8/13/13
 * Time: 11:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReaderFactory {
	 private static final Logger logger = LoggerFactory.getLogger(ReaderFactory.class);
    public Reader getspreadSheetReader(UploadedFile uploadedFile) throws IOException {
    	logger.debug("With in getspreadSheetReader start");
        Reader spreadSheetReader = null;
        String spreadSheetExtension = null;
        String fileName = null;

        FileInputStream fileInputStream = null;
        try {
            fileName = FilenameUtils.getName(uploadedFile.getName());
            logger.debug(" with in ReaderFactory----****fileName--"+fileName);
            //TODO: where the file is created?
            File dataFile = new File(fileName);
            FileOutputStream fos = new FileOutputStream(dataFile);
            fos.write(uploadedFile.getBytes());
            fos.flush();
            fos.close();
            fileInputStream = new FileInputStream(dataFile.getAbsolutePath());
        } catch (IOException e) {
            logger.debug("Sent to setup module Error"+e);
        }
        spreadSheetExtension = this.getExtension(fileName);
        if (spreadSheetExtension.contentEquals("xls")) {
            spreadSheetReader = new XLSReader(fileInputStream);

        } else if (spreadSheetExtension.contentEquals("xlsx")) {
            spreadSheetReader = new XLSXReader(fileInputStream);
        }
        return spreadSheetReader;
    }

    private String getExtension(String fileName) {
        String extension = null;
        try {
            int i = fileName.lastIndexOf('.');
            if (i > 0 && i < fileName.length() - 1) {
                extension = fileName.substring(i + 1).toLowerCase();
            }

        } catch (NullPointerException ex) {
            System.out.println("The file name is null! " + ex);
        } finally {
            return extension;
        }
    }
}
