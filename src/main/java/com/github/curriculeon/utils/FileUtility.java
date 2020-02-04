package com.github.curriculeon.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author leonhunter
 * @created 02/03/2020 - 7:09 PM
 */
public class FileUtility {
    private File file;

    public FileUtility(File file) {
        this.file = file;
    }

    public FileUtility(String filePath) {
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
