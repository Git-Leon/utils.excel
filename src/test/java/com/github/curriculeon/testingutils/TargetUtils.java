package com.github.curriculeon.testingutils;

import com.github.curriculeon.utils.FileUtility;

import java.io.File;

/**
 * @author leonhunter
 * @created 02/03/2020 - 7:01 PM
 */
public class TargetUtils {
    public static File getTargetDirectoryFile() {
        return new FileUtility(getTargetDirectoryPath()).getFile();
    }

    private static String getTargetDirectoryPath() {
        return new StringBuilder()
                .append(System.getProperty("user.dir"))
                .append("/target")
                .toString();
    }

}
