package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class OrganizationSection extends AbstractSection {
    private List<Organization> organizations = new ArrayList<>();

    public List<Organization> organizations() {
        return organizations;
    }

    public Organization add(Organization organization) {
        organizations.add(organization);
        return organization;
    }

    public void remove(int index) {
        organizations.remove(index);
    }

    public void println() {
        for (Organization organization : organizations) {
            System.out.println("\t\t" + organization.getTitle() + " " + organization.getWebsite());
            for (Period period : organization.periods()) {
                System.out.println("\t\t\t" + period.getStartDate() + " - " + period.getEndDate());
                System.out.println("\t\t\t\t" + period.getTitle());
                System.out.println("\t\t\t\t" + period.getDescription());
            }
        }
    }
}
