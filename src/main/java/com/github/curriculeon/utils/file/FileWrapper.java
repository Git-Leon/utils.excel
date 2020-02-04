package com.github.curriculeon.utils.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author leonhunter
 * @created 02/03/2020 - 7:09 PM
 */
public class FileWrapper {
    private File file;

    public FileWrapper(File file) {
        this.file = file;
    }

    public FileWrapper(String filePath) {
        this(new File(filePath));
    }

    public File getFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new Error(e);
            }
        }
        return file;
    }

    public String getFileExtension() {
        String fileName = file.getName();
        Integer lastPeriod = fileName.lastIndexOf('.');
        String fileExtension =fileName.substring(lastPeriod);
        return fileExtension;
    }

    public Boolean isDirectory() {
        return file.isDirectory();
    }

    public File copyTo(String fileName) {
        File destination;
        try {
            destination = new File(fileName);
            FileUtils.copyFile(file, destination);
        } catch (IOException e) {
            throw new Error(e);
        }
        return destination;
    }
}
