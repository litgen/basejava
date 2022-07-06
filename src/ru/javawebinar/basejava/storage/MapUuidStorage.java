package ru.javawebinar.basejava.storage;

import java.util.HashMap;
import java.util.Map;
import ru.javawebinar.basejava.model.Resume;

public class MapUuidStorage extends AbstractStorage {
  Map<String, Resume> storage = new HashMap<>();

  @Override
  public int size() {
    return storage.size();
  }

  @Override
  public void clear() {
    storage.clear();
  }

  @Override
  public Resume[] getAll() {
    return storage.values().toArray(new Resume[0]);
  }

  @Override
  protected Object getSearchKey(String uuid) {
    return uuid;
  }

  @Override
  protected void doUpdate(Resume r, Object searchKey) {
    storage.put(r.getUuid(), r);
  }

  @Override
  protected void doSave(Resume r, Object searchKey) {
    storage.put((String) searchKey, r);
  }

  @Override
  protected Resume doGet(Object searchKey) {
    return storage.get((String) searchKey);
  }

  @Override
  protected void doDelete(Object searchKey) {
    storage.remove((String) searchKey);
  }

  @Override
  protected boolean isExist(Object searchKey) {
    return storage.containsKey((String) searchKey);
  }
}
