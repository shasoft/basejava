package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends AbstractSection {
    private final List<String> strings;

    public ListSection() {
        strings = new ArrayList<>();
    }
    public ListSection(List<String> strings) {
        this.strings = strings;
    }

    public List<String> getStrings() {
        return strings;
    }

    public int hashCode() {
        return strings.hashCode();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return strings.equals(that.strings);
    }
    public String toString() {
        return "ListSection{" +
                strings.toString() +
                "}";
    }
}
