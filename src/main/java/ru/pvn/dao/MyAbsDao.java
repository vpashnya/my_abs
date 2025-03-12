package ru.pvn.dao;

import ru.pvn.objclasses.ObjectInDB;

import java.util.List;

public interface MyAbsDao<T extends ObjectInDB> {

    List<T> getAll();

    void save(T object);

    T get(int id);

    void delete(int id);

}
