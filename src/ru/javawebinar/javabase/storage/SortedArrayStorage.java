package ru.javawebinar.javabase.storage;

import ru.javawebinar.javabase.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{


  @Override
  public void save(Resume r) {

  }

  @Override
  public void delete(String uuid) {
    int index = getIndex(uuid);
    if (index == -1) {
      System.out.println("Resume " + uuid + " not exist");
    } else {
      --size;
      System.arraycopy(storage, index + 1, storage, index, size());
    }
  }

  @Override
  protected int getIndex(String uuid) {
    Resume searchKey = new Resume();
    searchKey.setUuid(uuid);
    return Arrays.binarySearch(storage, 0, size, searchKey);
  }
}