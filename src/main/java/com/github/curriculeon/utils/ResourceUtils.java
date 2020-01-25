package com.github.curriculeon.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ResourceUtils {
    public static File getResourcesDirectory() {
        return new File(getResourceDirectoryPath());
    }

    public static String getResourceDirectoryPath() {
        String currentDirectory = System.getProperty("user.dir");
        String resourceDirectory = "/src/main/resources/";
        return currentDirectory + resourceDirectory;
    }

    public static File getResourceFile(String fileName) {
        return new File(getResourceDirectoryPath() + fileName);
    }

    public static File duplicateFile(String fileName) {
        File source = ResourceUtils.getResourceFile(fileName);
        File destination;
        try {
            Long id = System.nanoTime();
            String parentDirectory = source.getParentFile().getAbsolutePath() + "/";
            String newFileName = source.getName();
            Integer lastPeriod = newFileName.lastIndexOf('.');
            String fileExtension = newFileName.substring(lastPeriod);
            newFileName = newFileName.substring(0, lastPeriod) + id + "." + fileExtension;
            destination = new File(parentDirectory + newFileName);
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            throw new Error(e);
        }
        return destination;
    }
}
