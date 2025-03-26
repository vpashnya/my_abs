package ru.pvn.levelup.abscore;

import lombok.Getter;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractAbsDao<T extends ObjectInDB> implements AbsDao<T> {
    private final Class<T> currentClass;
    @Getter
    private final EntityManager entityManager;

    public AbstractAbsDao(Class<T> currentClass, EntityManager entityManager) {
        this.currentClass = currentClass;
        this.entityManager = entityManager;
    }

    @Override
    public List getAll() {
        String sqlText = "from " + currentClass.getSimpleName() + " order by id desc ";
        List<T> result = entityManager.createQuery(sqlText, currentClass).getResultList();
        return result;
    }

    @Override
    public void save(T object) {
        entityManager.getTransaction().begin();
        try {
            if (object.getId() == null) {
                entityManager.persist(object);
            } else {
                entityManager.merge(object);
            }
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public T get(int id) {
        return entityManager.find(currentClass, id);
    }

    @Override
    public void delete(int id) {
        entityManager.getTransaction().begin();
        entityManager.remove(get(id));
        entityManager.getTransaction().commit();
    }

    @Override
    public Integer count() {
        Long l = (Long)entityManager.createQuery("select count(*) as cnt from " + currentClass.getSimpleName()).getSingleResult();
        return l.intValue();
    }

}
