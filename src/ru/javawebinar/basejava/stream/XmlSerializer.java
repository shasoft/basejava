package ru.javawebinar.basejava.stream;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlSerializer implements Serializer {
    private final XmlParser xmlParser;

    public XmlSerializer() {
        xmlParser = new XmlParser(
                Resume.class,
                Organization.class, OrganizationHead.class, Period.class,
                OrganizationSection.class,
                TextSection.class,
                ListSection.class
        );
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(r, w);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(r);
        }
    }
}