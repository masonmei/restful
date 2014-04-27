package org.personal.mason.rest.core.event.relation;

import org.personal.mason.rest.core.event.UpdateEvent;

import java.io.Serializable;

/**
 * Created by mason on 4/26/14.
 */
public class GenericRelationUpdateEvent<T, R, ID extends Serializable, RID extends Serializable> extends UpdateEvent {

    private ID objectKey;
    private T object;
    private RID relationObjectKey;
    private R relationObject;

    public GenericRelationUpdateEvent(ID objectKey, T object, RID relationObjectKey, R relationObject) {
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
}
