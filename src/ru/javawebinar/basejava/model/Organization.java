package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private final List<Period> periods;
    private final OrganizationHead head;

    public Organization(OrganizationHead head, List<Period> periods) {
        this.head = head;
        this.periods = periods;
    }

    public Organization(OrganizationHead head) {
        this(head, new ArrayList<>());
    }

    public Organization() {
        this(new OrganizationHead(), new ArrayList<>());
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

        return head.equals(that.head);
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
