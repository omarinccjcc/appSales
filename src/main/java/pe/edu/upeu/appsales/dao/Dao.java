package pe.edu.upeu.appsales.dao;

import pe.edu.upeu.appsales.model.BaseEntity;

import java.util.List;

public interface Dao<T extends BaseEntity, I> {
    List<T> findAll();

    T find(I id);

    T save(T entity);

    void delete(I id);

    void delete(T entity);
}
