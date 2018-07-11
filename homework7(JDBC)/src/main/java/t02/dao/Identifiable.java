package t02.dao;

public interface Identifiable<T> {
    long getId();

    T setId(long id);
}
