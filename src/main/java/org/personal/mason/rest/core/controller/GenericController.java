package org.personal.mason.rest.core.controller;

import org.personal.mason.rest.core.event.generic.*;
import org.personal.mason.rest.core.services.GenericService;
import org.personal.mason.rest.utils.ReflectionUtils;
import org.personal.mason.rest.utils.RestfulUrlsProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/25/14
 * Time: 2:03 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class GenericController<T, ID extends Serializable> {

    private final GenericService<T, ID> genericService;
    private final Class<T> genericTClass;
    private final Class<ID> genericIDClass;

    public GenericController(GenericService<T, ID> genericService) {
        this.genericService = genericService;
        Type[] types = ReflectionUtils.getParameterizedType(genericService);
        if(types == null || types.length < 2){
            throw new IllegalArgumentException("the generic service is invalid.");
        }

        this.genericTClass = (Class<T>)types[0];
        this.genericIDClass = (Class<ID>)types[1];
    }

    @RequestMapping(method = RequestMethod.GET,
            value = {"/{id}"},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<T> readObject(@PathVariable ID id) {
        GenericReadEvent<T, ID> event = genericService.readObject(new GenericRequestReadEvent<ID>(id));

        if (!event.isEntityFound()) {
            return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<T>(event.getObject(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<T>> readAllObjects() {
        GenericReadAllEvent<T> event = genericService.readAllObjects(new GenericRequestReadAllEvent());

        return new ResponseEntity<List<T>>(event.getReadableObjects(), HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.POST},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<T> createObject(@RequestBody T object, UriComponentsBuilder builder) {
        GenericCreatedEvent<T, ID> event = genericService.createObject(new GenericCreateEvent<T>(object));

        ID objectKey = event.getObjectKey();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path(RestfulUrlsProvider.getUri(genericTClass)).buildAndExpand(objectKey).toUri()
        );

        //TODO: read parameter and decide include the created Object or not
        T savedObject = event.getObject();

        return new ResponseEntity<T>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = {RequestMethod.PUT},
            value = {"/{id}"},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<T> updateObject(@PathVariable ID id, @RequestBody T object) {
        GenericUpdatedEvent<T, ID> event = genericService.updateObject(new GenericUpdateEvent<T, ID>(id, object));

        T savedObject = event.getObject();

        return new ResponseEntity<T>(savedObject, HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH},
            value = {"/{id}"},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<T> partialUpdateObject(@PathVariable ID id, @RequestBody T object) {
        GenericUpdatedEvent<T, ID> event = genericService.partialUpdateObject(new GenericUpdateEvent<T, ID>(id, object));

        T savedObject = event.getObject();

        return new ResponseEntity<T>(savedObject, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = {"/{id}" },
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<T> cancelOrder(@PathVariable ID id) {
        GenericDeletedEvent<T, ID> event = genericService.deleteObject(new GenericDeleteEvent<ID>(id));

        if (!event.isEntityFound()) {
            return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
        }

        T object = event.getObject();

        if (event.isDeletionCompleted()) {
            return new ResponseEntity<T>(object, HttpStatus.OK);
        }

        return new ResponseEntity<T>(object, HttpStatus.FORBIDDEN);
    }


}
