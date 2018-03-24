package com.db;


import com.exception.DataAlreadyExistsException;

public interface DocumentStore {

    <T> T get(String id, Class<T> c);
    long add(String id, Object object, int opTimeout);
    void delete(String id, long cas, int opTimeout);
    void update(String id, Object object);
    void create(String id, Object object) throws DataAlreadyExistsException;
    void delete(String id);
}
