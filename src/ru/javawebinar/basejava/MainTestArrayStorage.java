package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ListStorage;
import ru.javawebinar.basejava.storage.MapUuidStorage;
import ru.javawebinar.basejava.storage.Storage;

public class MainTestArrayStorage {
    static final Storage storage = new ListStorage();
    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "name1");
        Resume r2 = new Resume("uuid2", "name2");
        Resume r3 = new Resume("uuid3", "name3");

        storage.save(r3);
        storage.save(r1);
        storage.save(r2);

        System.out.println("Get r1: " + storage.get(r1.getUuid()));
        //        System.out.println("Get dummy: " + storage.get("dummy"));

        System.out.println(storage.getAllSorted());
        printAll();
        storage.delete(r1.getUuid());
        printAll();
        storage.clear();
        printAll();
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : storage.getAllSorted()) {
            System.out.println(r);
        }
        System.out.println("Size: " + storage.size());
    }
}