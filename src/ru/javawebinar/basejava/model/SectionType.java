package ru.javawebinar.basejava.model;

public enum SectionType {
  PERSONAL("Personal"),
  OBJECTIVE("Objective"),
  ACHIEVEMENT("Achievements"),
  QUALIFICATIONS("Qualifications"),
  EXPERIENCE("Experience"),
  EDUCATION("Education");

  private final String title;

  SectionType(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
