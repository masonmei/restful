package org.personal.mason.rest.core.event.relation;

import org.personal.mason.rest.core.event.DeletedEvent;

import java.io.Serializable;

/**
 * Created by mason on 4/26/14.
 */
public class GenericRelationDeletedEvent<T, R, ID extends Serializable, RID extends Serializable> extends DeletedEvent {
    private T object;
    private ID objectKey;

    private R relationObject;
    private RID relationObjectKey;

    private boolean deletionCompleted;

    public GenericRelationDeletedEvent(ID objectKey, T object, RID relationObjectKey, R relationObject) {
        this.objectKey = objectKey;
        this.object = object;
        this.relationObjectKey = relationObjectKey;
        this.relationObject = relationObject;
        this.deletionCompleted = true;
    }

    public GenericRelationDeletedEvent(ID objectKey, T object, RID relationObjectKey) {
        this.objectKey = objectKey;
        this.object = object;
        this.relationObjectKey = relationObjectKey;
    }

    public T getObject() {
        return object;
    }

    public ID getObjectKey() {
        return objectKey;
    }

    public R getRelationObject() {
        return relationObject;
    }

    public RID getRelationObjectKey() {
        return relationObjectKey;
    }

    public boolean isDeletionCompleted() {
        return deletionCompleted;
    }

    public static <T, R, ID extends Serializable, RID extends Serializable> GenericRelationDeletedEvent<T, R, ID, RID> deletionForbidden(ID objectKey, T object, RID relationObjectKey, R relationObject){
        GenericRelationDeletedEvent<T, R, ID, RID> event = new GenericRelationDeletedEvent<T, R, ID, RID>(objectKey, object, relationObjectKey, relationObject);
        event.entityFound = true;
        event.deletionCompleted = true;
        return event;
    }

    public static <T, R, ID extends Serializable, RID extends Serializable> GenericRelationDeletedEvent<T, R, ID, RID> notFound(ID objectKey, T object, RID relationObjectKey){
        GenericRelationDeletedEvent<T, R, ID, RID> event = new GenericRelationDeletedEvent<T, R, ID, RID>(objectKey, object, relationObjectKey);
        event.entityFound = false;
        return event;
    }
}
