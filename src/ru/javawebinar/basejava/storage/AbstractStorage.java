package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {
  protected int size = 0;

  public abstract void clear();

  public abstract Resume[] getAll();

  protected abstract boolean doUpdateIfPossible(Resume r);

  protected abstract boolean doSaveIfPossible(Resume r);

  protected abstract Resume doGetIfPossible(String uuid);

  protected abstract boolean doRemoveIfPossible(String uuid);

  public int size() {
    return size;
  }

  public void update(Resume r) {
    if (!doUpdateIfPossible(r)) {
      throw new NotExistStorageException(r.getUuid());
    }
  }

  public void save(Resume r) {
    if (doSaveIfPossible(r)) {
      size++;
    } else throw new ExistStorageException(r.getUuid());
  }

  public Resume get(String uuid) {
    Resume r = doGetIfPossible(uuid);
    if (r != null) {
      return r;
    } else throw new NotExistStorageException(uuid);
  }

  public void delete(String uuid) {
    if (doRemoveIfPossible(uuid)) {
      size--;
    } else {
      throw new NotExistStorageException(uuid);
    }
  }
}
