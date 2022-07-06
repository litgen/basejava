package ru.javawebinar.basejava.storage;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractStorageTest {
  protected final Storage storage;

  private static final String UUID_1 = "uuid1";
  private static final String UUID_2 = "uuid2";
  private static final String UUID_3 = "uuid3";
  private static final String UUID_4 = "uuid4";

  private static final Resume RESUME_1;
  private static final Resume RESUME_2;
  private static final Resume RESUME_3;
  private static final Resume RESUME_4;

  static {
    RESUME_1 = new Resume(UUID_1, "name0");
    RESUME_2 = new Resume(UUID_2, "name1");
    RESUME_3 = new Resume(UUID_3, "name2");
    RESUME_4 = new Resume(UUID_4, "name3");
  }

  protected AbstractStorageTest(Storage storage) {
    this.storage = storage;
  }

  @Before
  public void setUp() {
    storage.clear();
    storage.save(RESUME_2);
    storage.save(RESUME_3);
    storage.save(RESUME_1);
  }

  @Test
  public void size() {
    assertSize(3);
  }

  @Test
  public void clear() {
    storage.clear();
    assertSize(0);
  }

  @Test
  public void update() {
    Resume newResume = new Resume(UUID_1, "newName");
    storage.update(newResume);
    assertSame(newResume, storage.get(UUID_1));
  }

  @Test(expected = NotExistStorageException.class)
  public void updateNotExist() {
    storage.get("dummy");
  }

  @Test
  public void getAllSorted() {
    List<Resume> list = storage.getAllSorted();
    assertEquals(3, list.size());
    for (int i = 0; i < list.size(); i++) {
      assertEquals("name" + i, list.get(i).getFullName());
    }
  }

  @Test
  public void save() {
    storage.save(RESUME_4);
    assertSize(4);
    assertGet(RESUME_4);
  }

  @Test(expected = ExistStorageException.class)
  public void saveExist() {
    storage.save(RESUME_1);
  }

  @Test(expected = NotExistStorageException.class)
  public void delete() {
    storage.delete(UUID_1);
    assertSize(2);
    storage.get(UUID_1);
  }

  @Test(expected = NotExistStorageException.class)
  public void deleteNotExist() {
    storage.delete("dummy");
  }

  @Test
  public void get() {
    assertGet(RESUME_1);
    assertGet(RESUME_2);
    assertGet(RESUME_3);
  }

  @Test(expected = NotExistStorageException.class)
  public void getNotExist() {
    storage.get("dummy");
  }

  private void assertGet(Resume r) {
    assertEquals(r, storage.get(r.getUuid()));
  }

  private void assertSize(int size) {
    assertEquals(size, storage.size());
  }
}