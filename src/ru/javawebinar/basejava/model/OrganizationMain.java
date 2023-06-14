package ru.javawebinar.basejava.model;

public class OrganizationMain {
    private String title;
    private String website;

    public OrganizationMain(String title) {
        this(title, "");
    }

    public OrganizationMain(String title, String website) {
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

        OrganizationMain that = (OrganizationMain) o;

        if (!title.equals(that.title)) return false;
        if (!website.equals(that.website)) return false;
        return true;
    }

    public int hashCode() {
        return title.hashCode() ^ website.hashCode();
    }

    public String toString() {
        return "OrganizationMain{" +
                "title=" + title +
                ", website=" + website +
                "}";
    }
}
