package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
  protected static final int STORAGE_LIMIT = 10000;

  protected Resume[] storage = new Resume[STORAGE_LIMIT];

  @Override
  public void clear() {
    Arrays.fill(storage, 0, size, null);
    size = 0;
  }

  @Override
  public Resume[] getAll() {
    return Arrays.copyOfRange(storage, 0, size);
  }

  @Override
  protected boolean doUpdateIfPossible(Resume r) {
    int index = getIndex(r.getUuid());
    if (index > -1) {
      storage[index] = r;
      return true;
    }
    return false;
  }

  @Override
  protected boolean doSaveIfPossible(Resume r) {
    String uuid = r.getUuid();
    int index = getIndex(uuid);
    if (size == STORAGE_LIMIT) {
      throw new StorageException("Storage overflow", uuid);
    }
    boolean notExistValidator = index < 0;
    if (notExistValidator) {
      insertElement(r, index);
    }
    return notExistValidator;
  }

  @Override
  protected Resume doGetIfPossible(String uuid) {
    int index = getIndex(uuid);
    if (index > -1) {
      return storage[index];
    }
    return null;
  }

  protected boolean doRemoveIfPossible(String uuid) {
    int index = getIndex(uuid);
    boolean existValidator = index > -1;
    if (existValidator) {
      fillDeletedElement(index);
      storage[size - 1] = null;
    }
    return existValidator;
  }

  protected abstract void fillDeletedElement(int index);

  protected abstract void insertElement(Resume r, int index);

  protected abstract int getIndex(String uuid);
}