package ru.javawebinar.basejava;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;

public class MainReflection {

  public static void main(String[] args)
      throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    Resume r = new Resume();
    Field field = r.getClass().getDeclaredFields()[0];
    field.setAccessible(true);
    System.out.println(field.getName());
    System.out.println(field.get(r));
    field.set(r, "new_uuid");
    Method method = r.getClass().getDeclaredMethod("toString");
    System.out.println(method.invoke(r));
  }
}