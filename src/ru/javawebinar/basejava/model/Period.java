package ru.javawebinar.basejava.model;

import java.time.LocalDate;

public class Period {
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
}
