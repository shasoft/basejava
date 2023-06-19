package ru.javawebinar.basejava;

import java.io.File;

public class MainScanFiles {
    public static void main(String[] args) {
        try {
            printFile(0, new File("."));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printFile(int indent, File file) throws Exception {
        System.out.println((file.isFile() ? "Ф" : "Д") + "> " + ".".repeat(indent) + " " + file.toPath().getFileName());
        if (file.isDirectory()) {
            File[] items = file.listFiles();
            if (items != null) {
                for (File item : items) {
                    printFile(indent + 2, item);
                }
            }
        }
    }
}
