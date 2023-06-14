package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    private OrganizationHead head;

    private final List<Period> periods = new ArrayList<>();

    public Organization(OrganizationHead head) {
        this.head = head;
    }

    public OrganizationHead getHead() {
        return head;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!head.equals(that.head)) return false;
        return true;

    }

    public int hashCode() {
        return head.hashCode() ^ periods.hashCode();
    }
    public String toString() {
        return "Organization{" +
                "head=" + head.toString() +
                ", periods=" + periods.toString() +
                "}";
    }
}
