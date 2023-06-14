package ru.javawebinar.basejava;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainScanFiles {
    public static void main(String[] args) {
        try {
            List<File> dirs = new ArrayList<>();
            dirs.add(new File("."));
            while (!dirs.isEmpty()) {
                File dir = dirs.get(dirs.size() - 1);
                dirs.remove(dirs.size() - 1);

                System.out.println("Д: "+dir.getCanonicalPath());

                File[] items = dir.listFiles();
                for(File item : items ) {
                    if( item.isDirectory() ) {
                        dirs.add(item);
                    } else {
                        System.out.println("Ф: "+item.getCanonicalPath());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
}
