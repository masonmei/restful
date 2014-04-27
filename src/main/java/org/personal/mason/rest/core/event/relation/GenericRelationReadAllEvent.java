package org.personal.mason.rest.core.event.relation;

import org.personal.mason.rest.core.event.ReadEvent;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mason on 4/26/14.
 */
public class GenericRelationReadAllEvent<T, R, ID extends Serializable> extends ReadEvent{
    private final ID objectKey;
    private final T object;
    private final List<R> relationObjects;

    public GenericRelationReadAllEvent(ID objectKey, T object, List<R> relationObjects) {
        this.objectKey = objectKey;
        this.object = object;
        this.relationObjects = relationObjects;
    }

    public ID getObjectKey() {
        return objectKey;
    }

    public T getObject() {
        return object;
    }

    public List<R> getRelationObjects() {
        return relationObjects;
    }
}
