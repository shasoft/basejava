package ru.javawebinar.basejava.stream;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.stream.Serializer;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DataSerializer implements Serializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            //
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            //
            Map<SectionType, AbstractSection> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                dos.writeUTF(type.name());
                AbstractSection section = entry.getValue();
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> strings = ((ListSection) section).getStrings();
                        dos.writeInt(strings.size());
                        for (String str : strings) {
                            dos.writeUTF(str);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = ((OrganizationSection) section).getOrganizations();
                        dos.writeInt(organizations.size());
                        for (Organization organization : organizations) {
                            dos.writeUTF(organization.getHead().getTitle());
                            dos.writeUTF(organization.getHead().getWebsite());

                            List<Period> periods = organization.getPeriods();
                            dos.writeInt(periods.size());
                            for (Period period : periods) {
                                dos.writeUTF(period.getTitle());
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getEndDate().toString());
                                dos.writeUTF(period.getDescription());
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            //
            int sizeSections = dis.readInt();
            while(sizeSections>0) {
                SectionType type = SectionType.valueOf(dis.readUTF());
                AbstractSection section=null;
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        section = new TextSection(dis.readUTF());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSection listSection = new ListSection();
                        int sizeStrings = dis.readInt();
                        while(sizeStrings>0) {
                            listSection.getStrings().add(dis.readUTF());
                            sizeStrings--;
                        }
                        section = listSection;
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        OrganizationSection organizationSection = new OrganizationSection();
                        int sizeOrganizations = dis.readInt();
                        while(sizeOrganizations>0) {
                            Organization organization = new Organization(new OrganizationHead(dis.readUTF(), dis.readUTF()) );
                            int sizePeriods = dis.readInt();
                            while(sizePeriods>0) {
                                organization.getPeriods().add( new Period(
                                        dis.readUTF(),
                                        LocalDate.parse(dis.readUTF()),
                                        LocalDate.parse(dis.readUTF()),
                                        dis.readUTF()
                                ) );
                                //
                                sizePeriods--;
                            }
                            organizationSection.getOrganizations().add(organization);
                            //
                            sizeOrganizations--;
                        }
                        section = organizationSection;
                        break;
                }
                resume.addSection(type,section);
                sizeSections--;
            }
            return resume;
        }
    }
}
