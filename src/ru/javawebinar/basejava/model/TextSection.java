package ru.javawebinar.basejava.model;

public class TextSection extends AbstractSection {
    private String text;

    public TextSection(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection that = (TextSection) o;

        return text.equals(that.text);
    }

    public int hashCode() {
        return text.hashCode();
    }

    public String toString() {
        return "TextSection{" +
                text +
                "}";
    }
}
