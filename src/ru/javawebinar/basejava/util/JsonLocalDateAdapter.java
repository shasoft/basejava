package ru.javawebinar.basejava.util;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.io.IOException;
import java.time.LocalDate;

public class JsonLocalDateAdapter extends TypeAdapter<LocalDate> {
    @Override
    public LocalDate read(JsonReader jreader) throws IOException {
        return LocalDate.parse(jreader.nextString());
    }
    @Override
    public void write(JsonWriter jwriter, LocalDate value) throws IOException {
        jwriter.value(value.toString());
    }
}
