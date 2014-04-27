package org.personal.mason.rest.core.event.relation;

import org.personal.mason.rest.core.event.UpdatedEvent;

import java.io.Serializable;

/**
 * Created by mason on 4/26/14.
 */
public class GenericRelationUpdatedEvent<T, R, ID extends Serializable, RID extends Serializable> extends UpdatedEvent {
    private ID objectKey;
    private T object;
    private RID relationObjectKey;
    private R relationObject;

    public GenericRelationUpdatedEvent(ID objectKey, T object, RID relationObjectKey, R relationObject) {
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
