package com.github.curriculeon.utils.file.directory;

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

    public static File getDuplicateFile(String fileName, String newFileName) {
        File source = ResourceUtils.getResourceFile(fileName);
        String parentDirectory = source.getParentFile().getAbsolutePath() + "/";
        return new File(parentDirectory + newFileName);
    }

    public static File getDuplicateFile(String fileName) {
        File source = ResourceUtils.getResourceFile(fileName);
        String id = "_" + System.nanoTime();
        String newFileName = source.getName();
        Integer lastPeriod = newFileName.lastIndexOf('.');
        String fileExtension = newFileName.substring(lastPeriod);
        newFileName = newFileName.substring(0, lastPeriod) + id + fileExtension;
        return getDuplicateFile(fileName, newFileName);
    }

    public static File copyFile(String fileName) {
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
