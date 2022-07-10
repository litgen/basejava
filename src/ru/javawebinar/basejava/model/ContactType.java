package ru.javawebinar.basejava.model;

public enum ContactType {
  PHONE_NUMBER("Mobile Number"),
  SKYPE("Skype"),
  MAIL("Mail"),
  LINKEDIN("LinkedIn Profile"),
  GITHUB("GitHub Profile"),
  STACKOVERFLOW("Stackoverflow Profile"),
  HOME_PAGE("Home Page");

  private final String title;

  ContactType(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
