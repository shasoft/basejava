package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

/**
 * ru.javawebinar.basejava.model.Resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {

    // Unique identifier
    private final String uuid;

    private final String fullName;

    private final EnumMap<ContactType, String> contacts = new EnumMap<>(ContactType.class);

    private final EnumMap<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

    public Resume() {
        this(UUID.randomUUID().toString(), "Unknown");
    }

    public Resume(String uuid) {
        this(uuid, "Unknown");
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid.trim();
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

        if (!uuid.equals(resume.uuid)) return false;
        if (!fullName.equals(resume.fullName)) return false;
        if (!contacts.equals(resume.contacts)) return false;
        return sections.equals(resume.sections);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode() ^ fullName.hashCode() ^ contacts.hashCode() ^ sections.hashCode();
    }

    @Override
    public String toString() {
        return "Resume{uuid=" + uuid + ", fullName=" + fullName + ", contacts=" + contacts.hashCode() + ", sections=" + sections.hashCode() + "}";
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }

    public void addContact(ContactType type, String contact) {
        contacts.put(type, contact);
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public AbstractSection addSection(SectionType type, AbstractSection section) {
        sections.put(type, section);
        return section;
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public AbstractSection getSection(SectionType type) {
        return sections.get(type);
    }
}