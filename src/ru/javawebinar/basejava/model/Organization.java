package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    private String title;
    private String website;
    private final List<Period> periods = new ArrayList<>();

    public Organization(String title) {
        this.title = title;
        this.website = "";
    }

    public Organization(String title, String website) {
        this.title = title;
        this.website = website;
    }

    public String getTitle() {
        return title;
    }

    public String getWebsite() {
        return website;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!title.equals(that.title)) return false;
        if (!website.equals(that.website)) return false;
        if (!periods.equals(that.periods)) return false;
        return true;

    }

    public int hashCode() {
        return title.hashCode() ^ website.hashCode() ^ periods.hashCode();
    }

    public String toString() {
        return "Organization{" +
                "title=" + title +
                ", website=" + website +
                ", periods=" + periods.toString() +
                "}";
    }
}