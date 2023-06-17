package ru.javawebinar.basejava.model;

import com.google.gson.annotations.JsonAdapter;
import ru.javawebinar.basejava.util.JsonLocalDateAdapter;
import ru.javawebinar.basejava.util.XmlLocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class Period implements Serializable {
    private String title;
    private String description;
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    @JsonAdapter(JsonLocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    @JsonAdapter(JsonLocalDateAdapter.class)
    private LocalDate endDate;

    public Period() {
    }
    public Period(String title, LocalDate startDate, LocalDate endDate) {
        this(title, startDate, endDate, "");
    }

    public Period(String title, LocalDate startDate, LocalDate endDate, String description) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period that = (Period) o;

        if (!title.equals(that.title)) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (!description.equals(that.description)) return false;
        return true;
    }

    public int hashCode() {
        return title.hashCode() ^ startDate.hashCode() ^ endDate.hashCode() ^ description.hashCode();
    }
    public String toString() {
        return "Period{" +
                "title=" + title +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + "'" +
                "}";
    }
}
