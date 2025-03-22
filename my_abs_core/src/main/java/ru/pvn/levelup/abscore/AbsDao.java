package ru.pvn.levelup.abscore;

import java.util.List;

public interface AbsDao<T extends ObjectInDB> {

    List<T> getAll();

    void save(T object);

    T get(int id);

    void delete(int id);

    Integer count();

}
