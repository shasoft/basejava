package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final File PROPS = new File("config\\resumes.properties");
    private static final Config INSTANCE = new Config();

    private final Properties storage = new Properties();
    private final File storageDir;

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            storage.load(is);
            storageDir = new File(storage.getProperty("storage.dir"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public static Config get() {
        return INSTANCE;
    }

    public Properties getStorag() {
        return storage;
    }

    public File getStorageDir() {
        return storageDir;
    }
}
