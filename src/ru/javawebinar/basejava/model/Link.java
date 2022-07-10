package ru.javawebinar.basejava.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Link {
  private String value;
  private URL link;

  public Link(String value, String link) throws MalformedURLException {
    Objects.requireNonNull(value, "value must not be null");
    Objects.requireNonNull(link, "link must not be null");
    this.value = value;
    this.link = new URL(link);
  }

  public String getValue() {
    return value;
  }

  public URL getLink() {
    return link;
  }

  public void setValue(String value) {
    Objects.requireNonNull(value, "value must not be null");
    this.value = value;
  }

  public void setLink(String link) throws MalformedURLException {
    Objects.requireNonNull(link, "link must not be null");
    this.link = new URL(link);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Link link1 = (Link) o;

    if (!value.equals(link1.value)) {
      return false;
    }
    return link.equals(link1.link);
  }

  @Override
  public int hashCode() {
    int result = value.hashCode();
    result = 31 * result + link.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Links{" + "value='" + value + '\'' + ", link=" + link + '}';
  }
}