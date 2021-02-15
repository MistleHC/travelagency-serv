package travelagency.dao;

import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    boolean create (T entity);
    T findById(Long id);
    List<T> findAll();
    boolean update(T entity);
    boolean delete(Long id);
    void close();
}
