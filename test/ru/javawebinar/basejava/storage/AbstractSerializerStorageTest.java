package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Config;

import java.io.File;

class AbstractSerializerStorageTest extends AbstractStorageTest {
    //protected static final File STORAGE_DIRECTORY = new File("./STORAGE");
    protected static final File STORAGE_DIRECTORY = Config.get().getStorageDir();

    public AbstractSerializerStorageTest(Storage storage) {
        super(storage);
    }
}

