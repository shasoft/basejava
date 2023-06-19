package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrganizationSection extends AbstractSection implements Serializable {
    private final List<Organization> organizations;

    public OrganizationSection() {
        organizations = new ArrayList<>();
    }
    public OrganizationSection(List<Organization> organizations) {
        this.organizations = organizations;
    }
    public List<Organization> getOrganizations() {
        return organizations;
    }

    public int hashCode() {
        return organizations.hashCode();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

        return organizations.equals(that.organizations);
    }

    public String toString() {
        return "OrganizationSection{" +
                organizations.toString() +
                "}";
    }
}
