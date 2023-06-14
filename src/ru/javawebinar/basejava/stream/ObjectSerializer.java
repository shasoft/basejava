package ru.javawebinar.basejava.stream;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class ObjectSerializer implements Serializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(r);
        oos.close();
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try {
            ObjectInputStream ois = new ObjectInputStream(is);
            Resume r = (Resume) ois.readObject();
            ois.close();
            return r;
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null);
        }
    }
}