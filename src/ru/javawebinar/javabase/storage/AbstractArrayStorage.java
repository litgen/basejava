package ru.javawebinar.javabase.storage;

import java.util.Arrays;
import ru.javawebinar.javabase.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
  protected static final int STORAGE_LIMIT = 10000;
  final protected Resume[] storage = new Resume[STORAGE_LIMIT];
  protected int size = 0;

  public void clear() {
    Arrays.fill(storage, 0, size(), null);
    size = 0;
  }

  public void update(Resume r) {
    int index = getIndex(r.getUuid());
    if (index == -1) {
      System.out.println("Resume " + r.getUuid() + " not exist");
    } else {
      storage[index] = r;
    }
  }

  public Resume get(String uuid) {
    int index = getIndex(uuid);
    if (index == -1) {
      System.out.println("Resume " + uuid + " not exist");
      return null;
    }
    return storage[index];
  }

  public Resume[] getAll() {
    return Arrays.copyOfRange(storage, 0, size());
  }

  public int size() {
    return size;
  }

  public abstract void save(Resume r);
  public abstract void delete(String uuid);
  protected abstract int getIndex(String uuid);
}