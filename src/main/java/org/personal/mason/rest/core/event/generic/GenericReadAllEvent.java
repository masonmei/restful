package org.personal.mason.rest.core.event.generic;

import org.personal.mason.rest.core.event.ReadEvent;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 4:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericReadAllEvent<T> extends ReadEvent {

    private final List<T> readableObjects;

    public GenericReadAllEvent(List<T> readableObjects) {
        this.readableObjects = readableObjects;
    }

    public List<T> getReadableObjects() {
        return readableObjects;
    }
}
