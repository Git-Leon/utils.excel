package com.github.curriculeon.testingutils;

import com.github.curriculeon.utils.file.FileWrapper;

import java.io.File;

/**
 * @author leonhunter
 * @created 02/03/2020 - 7:01 PM
 */
public class TargetUtils {
    public static File getTargetDirectoryFile() {
        return new FileWrapper(getTargetDirectoryPath()).getFile();
    }

    public static String getTargetDirectoryPath() {
        return new StringBuilder()
                .append(System.getProperty("user.dir"))
                .append("/target")
                .toString();
    }
}
