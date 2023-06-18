// Функциональные интерфейсы в Java https://javarush.com/groups/posts/2866-funkcionaljhnihe-interfeysih-v-java
package ru.javawebinar.basejava.stream;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Collection;

public class DataSerializer implements Serializer {


    private <T> void writeCollection(DataOutputStream dos, Collection<T> entrys, EntryWriter<T> action) throws IOException {
        dos.writeInt(entrys.size());
        for (T entry : entrys) {
            action.write(entry);
        }
    }

    private <T> void readCollection(DataInputStream dis, EntryReader action) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            action.read();
        }
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            //
            writeCollection(dos, r.getContacts().entrySet(), (entry) -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            //
            writeCollection(dos, r.getSections().entrySet(), (entry) -> {
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
                        writeCollection(dos, ((ListSection) section).getStrings(), (str) -> {
                            dos.writeUTF(str);
                        });
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dos, ((OrganizationSection) section).getOrganizations(), (organization) -> {
                            dos.writeUTF(organization.getHead().getTitle());
                            dos.writeUTF(organization.getHead().getWebsite());

                            writeCollection(dos, organization.getPeriods(), (period) -> {
                                dos.writeUTF(period.getTitle());
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getEndDate().toString());
                                dos.writeUTF(period.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readCollection(dis, () -> {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            });
            //
            readCollection(dis, () -> {

                SectionType type = SectionType.valueOf(dis.readUTF());
                AbstractSection section = null;
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        section = new TextSection(dis.readUTF());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSection listSection = new ListSection();
                        readCollection(dis, () -> {
                            listSection.getStrings().add(dis.readUTF());
                        });
                        section = listSection;
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        OrganizationSection organizationSection = new OrganizationSection();
                        readCollection(dis, () -> {
                            Organization organization = new Organization(new OrganizationHead(dis.readUTF(), dis.readUTF()));
                            readCollection(dis, () -> {
                                organization.getPeriods().add(new Period(
                                        dis.readUTF(),
                                        LocalDate.parse(dis.readUTF()),
                                        LocalDate.parse(dis.readUTF()),
                                        dis.readUTF()
                                ));
                            });
                            organizationSection.getOrganizations().add(organization);
                        });
                        section = organizationSection;
                        break;
                }
                resume.addSection(type, section);
            });
            return resume;
        }
    }

    private interface EntryWriter<T> {
        public void write(T t) throws IOException;
    }

    private interface EntryReader {
        public void read() throws IOException;
    }
}
