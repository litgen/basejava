package ru.javawebinar.basejava.model;

import java.util.Date;
import java.util.TreeMap;

public class SectionOfSortedByDate extends Section {
  private final TreeMap<Date, SectionLinkForMap> map;

  public SectionOfSortedByDate() {
    map = new TreeMap<>();
  }
  public SectionOfSortedByDate(SectionLinkForMap... args) {
    map = new TreeMap<>();
    for (SectionLinkForMap element : args) {
      map.put(element.getFromDate(), element);
    }
  }

  public void putElement(Date fromDate, SectionLinkForMap elem) {
    map.put(fromDate, elem);
  }

  public void putElement(SectionLinkForMap elem) {
    putElement(elem.getFromDate(), elem);
  }

  public TreeMap<Date, SectionLinkForMap> getMap() {
    return map;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SectionOfSortedByDate that = (SectionOfSortedByDate) o;

    return map.equals(that.map);
  }

  @Override
  public int hashCode() {
    return map.hashCode();
  }

  @Override
  public String toString() {
    return "SectionLinkMap{" + "map=" + map + '}';
  }
}
