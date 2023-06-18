package ru.javawebinar.basejava.storage;

import java.io.File;

class AbstractSerializerStorageTest extends AbstractStorageTest {
    //protected static final File STORAGE_DIRECTORY = new File("./STORAGE");
    protected static final File STORAGE_DIRECTORY = new File("./STORAGE");

    public AbstractSerializerStorageTest(Storage storage) {
        super(storage);
    }
}

