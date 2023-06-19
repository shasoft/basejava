// Функциональные интерфейсы в Java https://javarush.com/groups/posts/2866-funkcionaljhnihe-interfeysih-v-java
package ru.javawebinar.basejava.stream;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataSerializer implements Serializer {

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
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getText());
                    case ACHIEVEMENT, QUALIFICATIONS ->
                            writeCollection(dos, ((ListSection) section).getStrings(), dos::writeUTF);
                    case EXPERIENCE, EDUCATION ->
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
            readCollection(dis, () ->
                    resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF())
            );
            //
            readCollection(dis, () -> {

                SectionType type = SectionType.valueOf(dis.readUTF());
                AbstractSection section = switch (type) {
                    case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
                    case ACHIEVEMENT, QUALIFICATIONS -> new ListSection(readList(dis, dis::readUTF));
                    case EXPERIENCE, EDUCATION -> new OrganizationSection(readList(dis, () -> new Organization(
                                    new OrganizationHead(dis.readUTF(), dis.readUTF()),
                                    readList(dis, () -> new Period(
                                                    dis.readUTF(),
                                                    LocalDate.parse(dis.readUTF()),
                                                    LocalDate.parse(dis.readUTF()),
                                                    dis.readUTF()
                                            )
                                    )
                            )
                    ));
                };
                resume.addSection(type, section);
            });
            return resume;
        }
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> entrys, EntryWriter<T> action) throws IOException {
        dos.writeInt(entrys.size());
        for (T entry : entrys) {
            action.write(entry);
        }
    }

    private void readCollection(DataInputStream dis, EntryReader action) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            action.read();
        }
    }

    private <T> List<T> readList(DataInputStream dis, EntryCreateAndReader<T> action) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(action.read());
        }
        return list;
    }

    private interface EntryWriter<T> {
        void write(T t) throws IOException;
    }

    private interface EntryReader {
        void read() throws IOException;
    }

    private interface EntryCreateAndReader<T> {
        T read() throws IOException;
    }

}
