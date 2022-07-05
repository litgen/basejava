package ru.javawebinar.basejava.storage;

import java.util.HashMap;
import java.util.Map;
import ru.javawebinar.basejava.model.Resume;

public class MapStorage extends AbstractStorage {
  Map<String, Resume> storage = new HashMap<>();

  @Override
  public void clear() {
    storage.clear();
    size = 0;
  }

  @Override
  public Resume[] getAll() {
    return storage.values().toArray(new Resume[0]);
  }

  @Override
  protected boolean doUpdateIfPossible(Resume r) {
    String uuid = r.getUuid();
    if (storage.containsKey(uuid)) {
      storage.put(uuid, r);
      return true;
    }
    return false;
  }

  @Override
  protected boolean doSaveIfPossible(Resume r) {
    String uuid = r.getUuid();
    if (!storage.containsKey(uuid)) {
      storage.put(uuid, r);
      return true;
    }
    return false;
  }

  @Override
  protected Resume doGetIfPossible(String uuid) {
    if (storage.containsKey(uuid)) {
      return storage.get(uuid);
    }
    return null;
  }

  protected boolean doRemoveIfPossible(String uuid) {
    boolean existValidator = storage.containsKey(uuid);
    if (existValidator) {
      storage.remove(uuid);
    }
    return existValidator;
  }
}
