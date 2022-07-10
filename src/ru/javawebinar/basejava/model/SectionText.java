package ru.javawebinar.basejava.model;

import java.util.Objects;

public class SectionText extends Section {
  public String content;

  public SectionText(String content) {
    Objects.requireNonNull(content, "content must not be null");
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SectionText that = (SectionText) o;

    return content.equals(that.content);
  }

  @Override
  public int hashCode() {
    return content.hashCode();
  }

  @Override
  public String toString() {
    return "SectionText{" + "content='" + content + '\'' + '}';
  }
}
