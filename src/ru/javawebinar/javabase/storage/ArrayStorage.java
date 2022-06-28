package ru.javawebinar.javabase.storage;

import ru.javawebinar.javabase.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void addResume(Resume r, int i) {
        storage[size] = r;
        size++;
    }

    @Override
    protected void rmResume(int i) {
        storage[i] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}