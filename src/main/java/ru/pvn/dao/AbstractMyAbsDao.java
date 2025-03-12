package ru.pvn.dao;

import ru.pvn.objclasses.ObjectInDB;
import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractMyAbsDao<T extends ObjectInDB> implements MyAbsDao<T> {
    private final Class<T> currentClass;
    private final EntityManager entityManager;

    protected AbstractMyAbsDao(Class<T> currentClass, EntityManager entityManager) {
        this.currentClass = currentClass;
        this.entityManager = entityManager;
    }

    @Override
    public List getAll() {
        String sqlText = "from " + currentClass.getSimpleName();
        List<T> result = entityManager.createQuery(sqlText, currentClass).getResultList();
        return result;
    }

    @Override
    public void save(T object) {
        entityManager.getTransaction().begin();
        if (object.getId() == null) {
            entityManager.persist(object);
        } else {
            entityManager.merge(object);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public T get(int id) {
        T object = entityManager.find(currentClass, id);
        return object;
    }

    @Override
    public void delete(int id) {
        entityManager.getTransaction().begin();
        entityManager.remove(get(id));
        entityManager.getTransaction().commit();
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
