package com.github.git_leon.utils.io;

import java.io.File;

/**
 * @author leonhunter
 * @created 02/03/2020 - 7:01 PM
 */
public enum DirectoryReference implements DirectoryReferenceInterface {
    TARGETDIRECTORY(DirectoryReferenceInterface
            .getLocalDirectoryBuilder()
            .append("/target/")),

    RESOURCEDIRECTORY(DirectoryReferenceInterface
            .getLocalDirectoryBuilder()
            .append("/src/main/resources/"));

    private final String path;

    DirectoryReference(StringBuilder path) {
        this.path = path.toString();
    }

    public String getDirectoryPath() {
        return path;
    }

    @Override
    public synchronized File getFileFromDirectory(String fileName) {
        return DirectoryReferenceInterface.super.getFileFromDirectory(fileName);
    }
}
