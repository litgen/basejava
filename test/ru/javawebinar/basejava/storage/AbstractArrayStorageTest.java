package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
  final private Storage storage;

  private static final String UUID_1 = "uuid1";
  private static final String UUID_2 = "uuid2";
  private static final String UUID_3 = "uuid3";
  private static final String UUID_4 = "uuid4";

  private final Resume r1 = new Resume(UUID_1);
  private final Resume r2 = new Resume(UUID_2);
  private final Resume r3 = new Resume(UUID_3);
  private final Resume r4 = new Resume(UUID_4);

  public AbstractArrayStorageTest(Storage storage) {
    this.storage = storage;
  }

  @Before
  public void setUp() {
    storage.clear();
    storage.save(r1);
    storage.save(r2);
    storage.save(r3);
  }

  @Test
  public void size() {
    Assert.assertNotEquals(4, storage.size());
    Assert.assertEquals(3, storage.size());
    storage.save(r4);
    Assert.assertEquals(4, storage.size());
    storage.delete(UUID_2);
    Assert.assertEquals(3, storage.size());
    storage.clear();
    Assert.assertEquals(0, storage.size());
  }

  @Test
  public void clear() {
    storage.clear();
    Assert.assertEquals(0, storage.size());
    storage.save(new Resume(UUID_4));
    storage.clear();
    Assert.assertEquals(0, storage.size());
  }

  @Test
  public void update() {

  }

  @Test
  public void getAll() {
    Resume[] standard = {r1, r2, r3};
    Resume[] compareTo = storage.getAll();
    for (int i = 0; i < storage.size(); i++) {
      Assert.assertEquals(standard[i], compareTo[i]);
    }
  }

  @Test
  public void save() {
    Resume[] compareTo = storage.getAll();
    for (int i = 0; i < storage.size(); i++) {
      Assert.assertNotEquals(r4, compareTo[i]);
    }
    storage.save(r4);
    Assert.assertEquals(r4, storage.get(UUID_4));
  }

  @Test
  public void delete() {
    Resume[] standard = {r1, r2, r3};
    Resume[] compareTo = storage.getAll();
    for (int i = 0; i < storage.size(); i++) {
      Assert.assertEquals(standard[i], compareTo[i]);
    }
    for (Resume resume : standard) {
      storage.delete(resume.getUuid());
    }
    Assert.assertEquals(0, storage.size());
  }

  @Test
  public void get() {
    Resume[] standard = {r1, r2, r3};
    for (int i = 0; i < storage.size(); i++) {
      Resume resume = standard[i];
      Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }
  }

  @Test(expected = NotExistStorageException.class)
  public void getNotExist() {
    storage.get(UUID_4);
  }

  @Test(expected = ExistStorageException.class)
  public void getExist() {
    storage.save(r3);

  }
}