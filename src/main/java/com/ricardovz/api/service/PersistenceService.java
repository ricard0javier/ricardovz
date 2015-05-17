package com.ricardovz.api.service;

import java.util.List;

/**
 * Created by ricardo on 04/05/2015.
 */
public interface PersistenceService<T> {

    /**
     * Saves a given object on database
     *
     * @param objectToSave {@link T} instance to be saved
     * @return {@link T} instance after been saved
     */
    T save(T objectToSave);

    /**
     * Returns a list of the all saved objects
     *
     * @return {@link List} of {@link T} objects
     */
    List<T> list();

    /**
     * Returns a single object that match the provided id
     *
     * @param id object id
     * @return {@link T} instance that match the provided id or null if none was found
     */
    T get(String id);

    /**
     * Mark the object as deleted
     *
     * @param id object id
     * @return object modified
     */
    T delete(String id);
}
