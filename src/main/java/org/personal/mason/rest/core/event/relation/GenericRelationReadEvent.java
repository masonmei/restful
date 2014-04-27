package org.personal.mason.rest.core.event.relation;

import org.personal.mason.rest.core.event.ReadEvent;

import java.io.Serializable;

/**
 * Created by mason on 4/26/14.
 */
public class GenericRelationReadEvent<T, R, ID extends Serializable, RID extends Serializable> extends ReadEvent {
    private final ID objectKey;
    private final T object;

    private final RID relationObjectKey;
    private R relationObject;

    public GenericRelationReadEvent(ID objectKey, T object, RID relationObjectKey) {
        this.objectKey = objectKey;
        this.object = object;
        this.relationObjectKey = relationObjectKey;
    }

    public GenericRelationReadEvent(ID objectKey, T object, RID relationObjectKey, R relationObject) {
        this.objectKey = objectKey;
        this.object = object;
        this.relationObjectKey = relationObjectKey;
        this.relationObject = relationObject;
    }

    public ID getObjectKey() {
        return objectKey;
    }

    public T getObject() {
        return object;
    }

    public RID getRelationObjectKey() {
        return relationObjectKey;
    }

    public R getRelationObject() {
        return relationObject;
    }

    public static <T, R, ID extends Serializable, RID extends Serializable> GenericRelationReadEvent<T, R, ID, RID> notFound(ID objectKey, T object, RID relationObjectKey){
        GenericRelationReadEvent<T, R, ID, RID> event = new GenericRelationReadEvent<T, R, ID, RID>(objectKey, object, relationObjectKey);
        event.entityFound = false;
        return event;
    }
}
