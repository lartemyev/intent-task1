package com.magento.task.framework.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class FilesUtils {
    public static void saveBytesAsFile(String path, byte[] fileBytes) {
        try (OutputStream out = new FileOutputStream(path)) {
            out.write(fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void createDirectoryIfNotExist(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static void saveTextToFile(String pathToFile, String fileName, String text) {
        byte[] fileBytes = text.getBytes(StandardCharsets.UTF_8);
        createDirectoryIfNotExist(pathToFile);
        saveBytesAsFile(pathToFile + File.separator + fileName, fileBytes);
    }

}
