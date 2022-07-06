package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.HashMap;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.MapUuidStorage;
import ru.javawebinar.basejava.storage.Storage;

public class MainTestArrayStorage {
    static final Storage storage = new MapUuidStorage();
    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");

        storage.save(r1);
        storage.save(r2);
        storage.save(r3);

        System.out.println("Get r1: " + storage.get(r1.getUuid()));
        //        System.out.println("Get dummy: " + storage.get("dummy"));

        System.out.println(Arrays.toString(storage.getAll()));
        printAll();
        storage.delete(r1.getUuid());
        printAll();
        storage.clear();
        printAll();
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : storage.getAll()) {
            System.out.println(r);
        }
        System.out.println("Size: " + storage.size());
    }
}