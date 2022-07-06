package ru.javawebinar.basejava.model;

import java.util.Objects;
import java.util.UUID;

public class Resume {

  private final String uuid;
  private final String fullName;

  public Resume(String fullName) {
    this(UUID.randomUUID().toString(), fullName);
  }

  public Resume() {
    this(UUID.randomUUID().toString(), "");
  }

  public Resume(String uuid, String fullName) {
    this.uuid = uuid;
    this.fullName = fullName;
  }

  public String getFullName() {
    return fullName;
  }

  public String getUuid() {
    return uuid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Resume resume = (Resume) o;

    if (!Objects.equals(uuid, resume.uuid)) {
      return false;
    }
    return Objects.equals(fullName, resume.fullName);
  }

  @Override
  public int hashCode() {
    int result = uuid != null ? uuid.hashCode() : 0;
    result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Resume{" + "uuid='" + uuid + '\'' + ", fullName='" + fullName + '\'' + '}';
  }
}