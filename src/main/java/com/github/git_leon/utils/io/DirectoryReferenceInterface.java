package com.github.git_leon.utils.io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author leonhunter
 * @created 02/04/2020 - 5:12 PM
 */

public interface DirectoryReferenceInterface {
    static StringBuilder getLocalDirectoryBuilder() {
        return new StringBuilder().append(System.getProperty("user.dir"));
    }

    String getDirectoryPath();

    default File getDirectoryFile() {
        return createFile(new File(getDirectoryPath()));
    }

    default File createFile(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new Error(String.format( new StringBuilder()
                        .append(e.getMessage())
                        .append("\nFailed to create file [ %s].")
                        .toString(), file.getAbsolutePath()));
            }
        }
        return file;
    }

    default File getFileFromDirectory(String fileName) {
        return new File(getDirectoryPath() + fileName);
    }

    default File copyFile(String fileName) {
        File source = getFileFromDirectory(fileName);
        File destination = getDuplicateFile(fileName);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            throw new Error(e);
        }
        return destination;
    }


    default File copyFileTo(String fileName, String newFilePath) {
        File source = getFileFromDirectory(fileName);
        File destination = new File(newFilePath);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            throw new Error(e);
        }
        return destination;
    }


    default File getDuplicateFile(String fileName, String newFileName) {
        File source = getFileFromDirectory(fileName);
        String parentDirectory = source.getParentFile().getAbsolutePath() + "/";
        return new File(parentDirectory + newFileName);
    }


    default File getDuplicateFile(String fileName) {
        File source = getFileFromDirectory(fileName);
        String id = "_" + System.nanoTime();
        String newFileName = source.getName();
        Integer lastPeriod = newFileName.lastIndexOf('.');
        String fileExtension = newFileName.substring(lastPeriod);
        newFileName = newFileName.substring(0, lastPeriod) + id + fileExtension;
        return getDuplicateFile(fileName, newFileName);
    }


    default String getFileExtension() {
        String fileName = getDirectoryFile().getName();
        Integer lastPeriod = fileName.lastIndexOf('.');
        String fileExtension = fileName.substring(lastPeriod);
        return fileExtension;
    }
}
