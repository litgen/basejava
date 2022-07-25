package ru.javawebinar.basejava.storage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
  private File directory;

  protected AbstractFileStorage(File directory) {
    Objects.requireNonNull(directory, "directory must not be null");
    if (!directory.isDirectory()) {
      throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
    }
    if (!directory.canRead() || !directory.canWrite()) {
      throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
    }
    this.directory = directory;
  }

  @Override
  public void clear() {
    File[] files = directory.listFiles();
    for (File file : files) {
      file.delete();
    }
  }

  @Override
  public int size() {
    File[] files = directory.listFiles();
    return files.length;
  }

  @Override
  protected void doUpdate(Resume r, File file) {
    try {
      file.createNewFile();
      doWrite(r, file);
    } catch (IOException e) {
      throw new StorageException("IO error", file.getName(), e);
    }
  }

  @Override
  protected void doSave(Resume r, File file) {
    try {
      file.createNewFile();
      doWrite(r, file);
    } catch (IOException e) {
      throw new StorageException("IO error", file.getName(), e);
    }
  }

  @Override
  protected Resume doGet(File file) {
    Resume r = doRead(file);
    return r;
  }

  @Override
  protected void doDelete(File file) {
    file.delete();
  }

  @Override
  protected List<Resume> doCopyAll() {
    File[] files = directory.listFiles();
    List<Resume> list = new ArrayList<>();
    for (File file : files) {
      list.add(doRead(file));
    }
    return list;
  }

  @Override
  protected boolean isExist(File file) {
    return file.exists();
  }

  @Override
  protected File getSearchKey(String uuid) {
    return new File(directory, uuid);
  }

  protected abstract void doWrite(Resume r, File file) throws IOException;

  protected abstract Resume doRead(File file);
}
