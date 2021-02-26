package travelagency.dao;

public interface GenericDao<T> extends AutoCloseable {
    boolean create (T entity);
    boolean update(T entity);
    boolean delete(Long id);
    void close();
}
