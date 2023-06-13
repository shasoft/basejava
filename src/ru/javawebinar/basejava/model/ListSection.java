package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends AbstractSection {
    private List<String> texts = new ArrayList<>();

    public List<String> all() {
        return texts;
    }

    public void add(String text) {
        texts.add(text);
    }

    public void remove(int index) {
        texts.remove(index);
    }

    public void println() {
        for(String text : texts) {
            System.out.println("\t\t"+text);
        }
    }
}
