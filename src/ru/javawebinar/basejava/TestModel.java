package ru.javawebinar.basejava;

import java.util.EnumMap;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.Section;
import ru.javawebinar.basejava.model.SectionList;
import ru.javawebinar.basejava.model.SectionText;
import ru.javawebinar.basejava.model.SectionType;

public class TestModel {
  public static void main(String... args) {
    Resume r = new Resume("Григорий Кислин");

    EnumMap<ContactType, String> contacts = new EnumMap<>(ContactType.class);

    contacts.put(ContactType.PHONE_NUMBER, "+7(921) 855-0482");
    contacts.put(ContactType.SKYPE, "grigory.kislin");
    contacts.put(ContactType.MAIL, "gkislin@yandex.ru");
    contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
    contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
    contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
    contacts.put(ContactType.HOME_PAGE, "http://gkislin.ru/");

    r.contacts = contacts;

    EnumMap<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    SectionText personal = new SectionText("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
    SectionText objective = new SectionText("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
    SectionList achievements = new SectionList();
    sections.put(SectionType.PERSONAL, personal);
    sections.put(SectionType.OBJECTIVE, objective);
  }
}
