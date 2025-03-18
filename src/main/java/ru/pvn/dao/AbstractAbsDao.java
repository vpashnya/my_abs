package ru.pvn.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.pvn.objclasses.ObjectInDB;
import javax.persistence.EntityManager;
import java.util.List;

@AllArgsConstructor
@Getter
public abstract class AbstractAbsDao<T extends ObjectInDB> implements AbsDao<T> {
    private final Class<T> currentClass;
    private final EntityManager entityManager;

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
        return entityManager.find(currentClass, id);
    }

    @Override
    public void delete(int id) {
        entityManager.getTransaction().begin();
        entityManager.remove(get(id));
        entityManager.getTransaction().commit();
    }

}
