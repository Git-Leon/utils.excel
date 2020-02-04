package com.github.curriculeon.utils.file;

import com.github.curriculeon.utils.file.FileWrapper;
import com.github.curriculeon.utils.file.directory.ResourceUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author leonhunter
 * @created 02/03/2020 - 7:01 PM
 */
public enum BuildUtils {
    TARGETDIRECTORY(getLocalDirectoryBuilder()
            .append("/target")),
    RESOURCEDIRECTORY(getLocalDirectoryBuilder()
            .append("/src/main/resources/"));

    private final String path;

    BuildUtils(StringBuilder path) {
        this.path = path.toString();
    }

    public String getDirectoryPath() {
        return path;
    }

    public File getDirectoryFile() {
        return new FileWrapper(path).getFile();
    }

    public File getFileFromDirectory(String fileName) {
        return new File(getDirectoryPath() + fileName);
    }

    public File copyFile(String fileName) {
        File source = getFileFromDirectory(fileName);
        File destination = getDuplicateFile(fileName);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            throw new Error(e);
        }
        return destination;
    }


    public File getDuplicateFile(String fileName, String newFileName) {
        File source = getFileFromDirectory(fileName);
        String parentDirectory = source.getParentFile().getAbsolutePath() + "/";
        return new File(parentDirectory + newFileName);
    }

    public File getDuplicateFile(String fileName) {
        File source = getFileFromDirectory(fileName);
        String id = "_" + System.nanoTime();
        String newFileName = source.getName();
        Integer lastPeriod = newFileName.lastIndexOf('.');
        String fileExtension = newFileName.substring(lastPeriod);
        newFileName = newFileName.substring(0, lastPeriod) + id + fileExtension;
        return getDuplicateFile(fileName, newFileName);
    }


    public String getFileExtension() {
        String fileName = getDirectoryFile().getName();
        Integer lastPeriod = fileName.lastIndexOf('.');
        String fileExtension = fileName.substring(lastPeriod);
        return fileExtension;
    }


    private static StringBuilder getLocalDirectoryBuilder() {
        return new StringBuilder().append(System.getProperty("user.dir"));
    }
}
