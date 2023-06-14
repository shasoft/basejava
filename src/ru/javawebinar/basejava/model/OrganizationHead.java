package ru.javawebinar.basejava.model;

import java.io.Serializable;

public class OrganizationHead implements Serializable {
    private String title;
    private String website;

    public OrganizationHead(String title) {
        this(title, "");

    }

    public OrganizationHead(String title, String website) {
        this.title = title;
        this.website = website;
    }

    public String getTitle() {
        return title;
    }

    public String getWebsite() {
        return website;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationHead that = (OrganizationHead) o;

        if (!title.equals(that.title)) return false;
        if (!website.equals(that.website)) return false;
        return true;
    }

    public int hashCode() {
        return title.hashCode() ^ website.hashCode();
    }

    public String toString() {
        return "OrganizationHead{" +
                "title=" + title +
                ", website=" + website +
                "}";
    }
}
