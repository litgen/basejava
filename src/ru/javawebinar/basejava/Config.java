package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

public class Config {
  protected static final File PROPS = new File("./config/resumes.properties");
  private static final Config INSTANCE = new Config();

  private final File storageDir;

  private Config() {
    final Properties props = new Properties();
    try (InputStream is = Files.newInputStream(PROPS.toPath())) {
      props.load(is);
      storageDir = new File(props.getProperty("storage.dir"));
    } catch (IOException e) {
      throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
    }
  }

  public static Config get() {return INSTANCE;}

  public File getStorageDir() {
    return storageDir;
  }
}
