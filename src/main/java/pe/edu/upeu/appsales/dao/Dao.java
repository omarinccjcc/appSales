package pe.edu.upeu.appsales.dao;

import pe.edu.upeu.appsales.model.EntityGeneric;

import java.util.List;

public interface Dao<T extends EntityGeneric, I> {
    List<T> findAll();

    T find(I id);

    T save(T entity);

    void delete(I id);

    void delete(T entity);
}
