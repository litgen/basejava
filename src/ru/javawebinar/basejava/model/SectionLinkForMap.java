package ru.javawebinar.basejava.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class SectionLinkForMap {
  private static final String PATTERN = "mm/yyyy";
  private final SimpleDateFormat format = new SimpleDateFormat(PATTERN);
  private Date fromDate;
  private Date toDate;
  private Link title;
  private String content;

  public SectionLinkForMap(String fromDate, String toDate, Link title, String content)
      throws ParseException {
    Objects.requireNonNull(fromDate, "fromDate have to be not null");
    Objects.requireNonNull(toDate, "toDate have to be not null");
    Objects.requireNonNull(title, "title have to be not null");
    Objects.requireNonNull(content, "content have to be not null");
    this.fromDate = format.parse(fromDate);
    this.toDate = format.parse(toDate);
    this.title = title;
    this.content = content;
  }

  public String getPattern() {
    return PATTERN;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  public Link getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public void setTitle(Link title) {
    this.title = title;
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

    SectionLinkForMap that = (SectionLinkForMap) o;

    if (!format.equals(that.format)) {
      return false;
    }
    if (!fromDate.equals(that.fromDate)) {
      return false;
    }
    if (!toDate.equals(that.toDate)) {
      return false;
    }
    if (!title.equals(that.title)) {
      return false;
    }
    return content.equals(that.content);
  }

  @Override
  public int hashCode() {
    int result = format.hashCode();
    result = 31 * result + fromDate.hashCode();
    result = 31 * result + toDate.hashCode();
    result = 31 * result + title.hashCode();
    result = 31 * result + content.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "SectionLinks{" + "format=" + format + ", fromDate=" + fromDate + ", toDate=" + toDate
        + ", title=" + title + ", content='" + content + '\'' + '}';
  }
}
