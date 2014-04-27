package org.personal.mason.rest.core.event.relation;

import org.personal.mason.rest.core.event.RequestReadEvent;

import java.io.Serializable;

/**
 * Created by mason on 4/26/14.
 */
public class GenericRelationRequestReadEvent<T, R, ID extends Serializable, RID extends Serializable> extends RequestReadEvent {
    private ID objectKey;
    private T object;
    private RID relationObjectKey;
    private R relation;

    public GenericRelationRequestReadEvent(ID objectKey, T object, RID relationObjectKey) {
        this.objectKey = objectKey;
        this.object = object;
        this.relationObjectKey = relationObjectKey;
    }

    public GenericRelationRequestReadEvent(ID objectKey, T object, RID relationObjectKey, R relation) {
        this.objectKey = objectKey;
        this.object = object;
        this.relationObjectKey = relationObjectKey;
        this.relation = relation;
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

    public R getRelation() {
        return relation;
    }
}
