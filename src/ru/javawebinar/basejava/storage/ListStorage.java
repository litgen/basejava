package ru.javawebinar.basejava.storage;

import java.util.ArrayList;
import ru.javawebinar.basejava.model.Resume;

public class ListStorage extends AbstractStorage {
  ArrayList<Resume> storage = new ArrayList<>();

  @Override
  public void clear() {
    storage.clear();
    size = 0;
  }

  @Override
  public Resume[] getAll() {
    return storage.toArray(new Resume[0]);
  }

  private int getIndex(String uuid) {
    Resume[] arr = getAll();
    for (int i = 0; i < arr.length; i++) {
      if (uuid.equals(arr[i].getUuid())) return i;
    }
    return -1;
  }

  @Override
  protected boolean doUpdateIfPossible(Resume r) {
    String uuid = r.getUuid();
    int index = getIndex(uuid);
    if (index > -1) {
      storage.set(index, r);
      return true;
    }
    return false;
  }

  @Override
  protected boolean doSaveIfPossible(Resume r) {
    if (!storage.contains(r)) {
      storage.add(r);
      return true;
    }
    return false;
  }

  @Override
  protected Resume doGetIfPossible(String uuid) {
    int index = getIndex(uuid);
    if (index > -1) {
      return storage.get(index);
    }
    return null;
  }

  @Override
  protected boolean doRemoveIfPossible(String uuid) {
    int index = getIndex(uuid);
    boolean existValidator = index > -1;
    if (existValidator) {
      storage.remove(index);
    }
    return existValidator;
  }
}
