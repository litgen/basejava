package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Organization {
  private final Link homePage;

  private List<Period> periods;
  private final String title;
  private final String description;

  public Organization(String name, String url, List<Period> periods, String title, String description) {
    Objects.requireNonNull(periods, "periods must not be null");
    Objects.requireNonNull(title, "title must not be null");
    this.homePage = new Link(name, url);
    this.periods = periods;
    this.title = title;
    this.description = description;
  }

  public List<Period> getPeriods() {
    return periods;
  }

  public void setPeriods(List<Period> periods) {
    this.periods = periods;
  }

  public void addPeriod(Period period) {
    periods.add(period);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Organization that = (Organization) o;
    return homePage.equals(that.homePage) && periods.equals(that.periods) && title.equals(
        that.title) && description.equals(that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(homePage, periods, title, description);
  }

  @Override
  public String toString() {
    return "Organization{" + "homePage=" + homePage + ", periods=" + periods + ", title='" + title
        + '\'' + ", description='" + description + '\'' + '}';
  }
}