package ru.javawebinar.basejava.storage.serializer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Map;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.model.Organization.Position;

public class DataStreamSerializer implements StreamSerializer {

  @Override
  public void doWrite(Resume r, OutputStream os) throws IOException {
    try (DataOutputStream dos = new DataOutputStream(os)) {
      dos.writeUTF(r.getUuid());
      dos.writeUTF(r.getFullName());
      Map<ContactType, String> contacts = r.getContacts();
      dos.writeInt(contacts.size());

      for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
        dos.writeUTF(entry.getKey().name());
        dos.writeUTF(entry.getValue());
      }

      Map<SectionType, Section> sections = r.getSections();
      dos.writeInt(sections.size());

      for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
        SectionType st = entry.getKey();
        Section section = entry.getValue();
        dos.writeUTF(st.name());

        switch (st) {
          case PERSONAL:
          case OBJECTIVE:
            dos.writeUTF(((TextSection) section).getContent());
            break;
          case ACHIEVEMENT:
          case QUALIFICATIONS:
            List<String> ls = ((ListSection) section).getItems();
            dos.writeInt(ls.size());

            for (String item : ls) {
              dos.writeUTF(item);
            }
            break;
          case EXPERIENCE: case EDUCATION:
            List<Organization> organizations = ((OrganizationSection) section).getOrganizations();
            dos.writeInt(organizations.size());

            for (Organization organization : organizations) {
              dos.writeUTF(organization.getHomePage().getName());
              dos.writeUTF(organization.getHomePage().getUrl());

              List<Position> positions = organization.getPositions();
              dos.writeInt(positions.size());

              for (Position position : positions) {
                dos.writeUTF(position.getStartDate().toString());
                dos.writeUTF(position.getEndDate().toString());
                dos.writeUTF(position.getTitle());
                dos.writeUTF(position.getDescription());
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
      int contactSize = dis.readInt();
      for (int i = 0; i < contactSize; i++) {
        resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
      }

      int sectionSize = dis.readInt();
      for (int i = 0; i < sectionSize; i++) {
        SectionType st = SectionType.valueOf(dis.readUTF());

        switch (st) {
          case PERSONAL:
          case OBJECTIVE:
            resume.addSection(st, new TextSection(dis.readUTF()));
            break;
          case ACHIEVEMENT:
          case QUALIFICATIONS:
            List<String> sectionList = new ArrayList<>();
            int listSize = dis.readInt();

            for (int j = 0; j < listSize; j++) {
              sectionList.add(dis.readUTF());
            }
            resume.addSection(st, new ListSection(sectionList));
            break;
          case EXPERIENCE: case EDUCATION:
            List<Organization> organizations = new ArrayList<>();
            int organizationsSize = dis.readInt();

            for (int j = 0; j < organizationsSize; j++) {
              Link link = new Link(dis.readUTF(), dis.readUTF());
              List<Position> positions = new ArrayList<>();
              int positionsSize = dis.readInt();

              for (int k = 0; k < positionsSize; k++) {
                LocalDate startDate = LocalDate.parse(dis.readUTF());
                LocalDate endDate = LocalDate.parse(dis.readUTF());
                String title = dis.readUTF();
                String description = dis.readUTF();
                Position position = new Position(startDate, endDate, title, description);
                positions.add(position);
              }

              organizations.add(new Organization(link, positions));
            }
            resume.addSection(st, new OrganizationSection(organizations));
            break;
        }
      }

      return resume;
    }
  }
}