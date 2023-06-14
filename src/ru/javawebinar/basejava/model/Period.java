package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Period implements Serializable {
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    public Period(String title, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = "";
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
