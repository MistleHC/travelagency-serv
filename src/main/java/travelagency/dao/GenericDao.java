package travelagency.dao;

import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    boolean create (T entity);
    T findById(int id);
    List<T> findAll();
    boolean update(T entity);
    boolean delete(int id);
    void close();
}
