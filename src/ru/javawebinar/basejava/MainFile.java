package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
  public static void main(String[] args) {
    String filePath = ".\\.gitignore";
    String projectPath = "./src/ru/javawebinar/basejava";

    File file = new File(filePath);
    try {
      System.out.println(file.getCanonicalPath());
    } catch (IOException e) {
      throw new RuntimeException("Error", e);
    }

    File dir = new File(projectPath);
    System.out.println(dir.isDirectory());
    String[] list = dir.list();
    if (list != null) {
      for (String name : list) {
        System.out.println(name);
      }
    }

    try (FileInputStream fis = new FileInputStream(filePath)) {
      System.out.println("fis: " + fis.read());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try {
      deepFilesPrint(projectPath, "");
    } catch (IOException e) {
      throw new RuntimeException("Error", e);
    }
  }

  private static void deepFilesPrint(String projectPath, String space) throws IOException {
    File coreDir = new File(projectPath);
    if (!coreDir.isDirectory()) {
      System.out.println(space + coreDir.getName());
    } else {
      System.out.println(space + coreDir.getName() + ":");
      File[] list = coreDir.listFiles();
      for (File file : list) {
        deepFilesPrint(file.getCanonicalPath(), space + "   ");
      }
    }
  }
}