package ru.javawebinar.basejava.model;

import java.util.*;

/**
 * ru.javawebinar.basejava.model.Resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;

    private String fullName;

    private EnumMap <ContactType, String> contacts = new EnumMap<>(ContactType.class);

    private EnumMap<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

    public Resume() {
        this(UUID.randomUUID().toString());
        this.fullName = "Unknown";
    }

    public Resume(String uuid) {
        this.uuid = uuid;
        this.fullName = "Unknown";
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }

    public void addContact(ContactType type, String contact) {
        contacts.put(type, contact);
    }

    public Map<ContactType, String> allContacts() {
        return contacts;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public AbstractSection addSection(SectionType type, AbstractSection section) {
        sections.put(type, section);
        return section;
    }

    public Map<SectionType, AbstractSection> allSections() {
        return sections;
    }

    public AbstractSection getSection(SectionType type) {
        return sections.get(type);
    }

    public void println() {
        System.out.println(getUuid()+" "+getFullName());
        for (Map.Entry<ContactType, String> entry : allContacts().entrySet()) {
            System.out.println("\t"+entry.getKey().getTitle() + ": " + entry.getValue());
        }
        for (Map.Entry<SectionType, AbstractSection> entry : allSections().entrySet()) {
            System.out.println("\t*** "+entry.getKey().getTitle()+" ***");
            entry.getValue().println();
        }
    }
}