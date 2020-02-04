package com.github.curriculeon.utils.file.directory.utils;

import com.github.curriculeon.utils.file.directory.ResourceUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author leonhunter
 * @created 02/04/2020 - 5:12 PM
 */
public interface DirectoryUtilityInterface {
    default File getDirectory() {
        return new File(getDirectoryPath());
    }

    default String getDirectoryPath() {
        String currentDirectory = System.getProperty("user.dir");
        String resourceDirectory = "/src/main/resources/";
        return currentDirectory + resourceDirectory;
    }

    default File getFileFromDirectory(String fileName) {
        return new File(getDirectoryPath() + fileName);
    }

    default File getDuplicateFile(String fileName, String newFileName) {
        File source = ResourceUtils.getResourceFile(fileName);
        String parentDirectory = source.getParentFile().getAbsolutePath() + "/";
        return new File(parentDirectory + newFileName);
    }

    default File getDuplicateFile(String fileName) {
        File source = ResourceUtils.getResourceFile(fileName);
        String id = "_" + System.nanoTime();
        String newFileName = source.getName();
        Integer lastPeriod = newFileName.lastIndexOf('.');
        String fileExtension = newFileName.substring(lastPeriod);
        newFileName = newFileName.substring(0, lastPeriod) + id + fileExtension;
        return getDuplicateFile(fileName, newFileName);
    }

    default File copyFile(String fileName) {
        File source = ResourceUtils.getResourceFile(fileName);
        File destination = getDuplicateFile(fileName);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            throw new Error(e);
        }
        return destination;
    }
}
