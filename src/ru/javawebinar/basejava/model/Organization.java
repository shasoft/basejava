package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    private String title;
    private String website;
    private List<Period> periods = new ArrayList<>();

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

    public List<Period> periods() {
        return periods;
    }

    public void addPeriod(Period period) {
        periods.add(period);
    }

    public void removePeriod(int index) {
        periods.remove(index);
    }
}
