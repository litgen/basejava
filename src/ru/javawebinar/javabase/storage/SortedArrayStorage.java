package ru.javawebinar.javabase.storage;

import ru.javawebinar.javabase.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
  @Override
  protected void addResume(Resume r, int i) {
    i = -i - 1;
    ++size;
    System.arraycopy(storage, i, storage, i + 1, size());
    storage[i] = r;
  }

  @Override
  protected void rmResume(int i) {
    --size;
    System.arraycopy(storage, i + 1, storage, i, size());
  }

  @Override
  protected int getIndex(String uuid) {
    Resume searchKey = new Resume();
    searchKey.setUuid(uuid);
    return Arrays.binarySearch(storage, 0, size, searchKey);
  }
}