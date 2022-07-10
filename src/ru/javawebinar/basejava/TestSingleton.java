package ru.javawebinar.basejava;

import java.util.EnumMap;

public class TestSingleton {
  private static TestSingleton instance;

  public static TestSingleton getInstance() {
    if (instance == null) {
      instance = new TestSingleton();
    }
    return instance;
  }

  private TestSingleton() {
  }

  public static void main(String[] args) {
    EnumMap<Color, Integer> colors = new EnumMap<>(Color.class);

    colors.put(Color.RED, 1);
    colors.put(Color.GREEN, 2);
    colors.put(Color.BLUE, 3);
    colors.put(Color.WHITE, 4);

    colors.replace(Color.RED, 11);
    colors.replace(Color.GREEN, 2, 12);

    System.out.println(colors);
  }

  enum Color {
    RED,
    GREEN,
    BLUE,
    WHITE
  }
}