package ru.javawebinar.basejava.model;

import java.time.LocalDate;

public class TextSection extends AbstractSection {
    private String text;

    public TextSection(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void println() {
        System.out.println("\t\t"+text);
    }
}
