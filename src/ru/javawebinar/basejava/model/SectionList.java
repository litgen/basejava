package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SectionList extends Section {
  private List<String> list;

  public SectionList() {
    list = new ArrayList<>();
  }

  public SectionList(List<String> list) {
    Objects.requireNonNull(list, "list can be empty but not null");
    this.list = list;
  }

  public SectionList(String... contents) {
    list = new ArrayList<>();
    list.addAll(Arrays.asList(contents));
  }

  public void addValueToList(String content) {
    Objects.requireNonNull(content, "content must not be null");
    list.add(content);
  }

  public List<String> getList() {
    return list;
  }

  public void setList(List<String> list) {
    Objects.requireNonNull(list, "list can be empty but not null");
    this.list = list;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SectionList that = (SectionList) o;

    return list.equals(that.list);
  }

  @Override
  public int hashCode() {
    return list.hashCode();
  }

  @Override
  public String toString() {
    return "SectionList{" + "list=" + list + '}';
  }
}
