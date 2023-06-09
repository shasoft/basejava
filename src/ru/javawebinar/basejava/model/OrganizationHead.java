package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationHead implements Serializable {
    private final String title;
    private final String website;

    public OrganizationHead() {
        this("", "");
    }

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
        return website.equals(that.website);
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
