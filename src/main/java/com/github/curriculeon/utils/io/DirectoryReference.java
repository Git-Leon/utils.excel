package com.github.curriculeon.utils.io;

/**
 * @author leonhunter
 * @created 02/03/2020 - 7:01 PM
 */
public enum DirectoryReference implements DirectoryReferenceInterface {
    TARGETDIRECTORY(DirectoryReferenceInterface
            .getLocalDirectoryBuilder()
            .append("/target")),

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
}