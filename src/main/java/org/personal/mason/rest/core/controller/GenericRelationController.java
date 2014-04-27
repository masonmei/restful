package org.personal.mason.rest.core.controller;

import org.personal.mason.rest.core.event.generic.*;
import org.personal.mason.rest.core.event.relation.*;
import org.personal.mason.rest.core.services.GenericRelationService;
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
import sun.plugin2.main.server.ResultID;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/25/14
 * Time: 5:43 PM
 * To change this template use File | Settings | File Templates.
 */

public abstract class GenericRelationController<T, R, ID extends Serializable, RID extends Serializable> {
    private final GenericRelationService<T, R, ID, RID> genericRelationService;
    private final Class<T> genericTClass;
    private final Class<R> genericRClass;
    private final Class<ID> genericIDClass;
    private final Class<RID> genericRIDClass;
    public GenericRelationController(GenericRelationService<T, R, ID, RID> genericRelationService) {
        this.genericRelationService = genericRelationService;
        Type[] types = ReflectionUtils.getParameterizedType(genericRelationService);
        if(types == null || types.length < 4){
            throw new IllegalArgumentException("the generic service is invalid.");
        }

        this.genericTClass = (Class<T>)types[0];
        this.genericRClass = (Class<R>)types[1];
        this.genericIDClass = (Class<ID>)types[2];
        this.genericRIDClass = (Class<RID>)types[3];
    }

    @RequestMapping(method = RequestMethod.GET,
            value = {"/{relationId}"},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<R> readRelationObject(@PathVariable(value = "id") ID id,
                                        @PathVariable(value = "relationId") RID relationId) {
        GenericRelationReadEvent<T, R, ID, RID> event = genericRelationService.readRelationObject(new GenericRelationRequestReadEvent<T, R, ID, RID>(id, null, relationId));

        if (!event.isEntityFound()) {
            return new ResponseEntity<R>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<R>(event.getRelationObject(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<R>> readAllRelationObjects(@PathVariable(value = "id") ID id) {
        GenericRelationReadAllEvent<T, R, ID> event = genericRelationService.readAllRelationObjects(new GenericRelationRequestReadAllEvent<T, R, ID>(id, null));

        return new ResponseEntity<List<R>>(event.getRelationObjects(), HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.POST},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<R> createRelationObject(@PathVariable(value = "id") ID id,
                                                  @RequestBody R relationObject, UriComponentsBuilder builder) {
        GenericRelationCreatedEvent<T, R, ID, RID> event = genericRelationService.createRelationObject(new GenericRelationCreateEvent<T, R, ID>(id, null, relationObject));

        ID objectKey = event.getObjectKey();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path(RestfulUrlsProvider.getUri(genericRClass)).buildAndExpand(objectKey).toUri()
        );

        //TODO: read parameter and decide include the created Object or not
        R savedObject = event.getRelationObject();

        return new ResponseEntity<R>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = {RequestMethod.PUT},
            value = {"/{relationId}"},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<R> updateRelationObject(@PathVariable(value = "id") ID id,
                                          @PathVariable(value = "relationId") RID relationId,
                                          @RequestBody R relationObject) {
        GenericRelationUpdatedEvent<T, R, ID, RID> event = genericRelationService.updateRelationObject(new GenericRelationUpdateEvent<T, R, ID, RID>(id, null, relationId, relationObject));

        R savedObject = event.getRelationObject();

        return new ResponseEntity<R>(savedObject, HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH},
            value = {"/{relationId}"},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<R> partialUpdateRelationObject(@PathVariable(value = "id") ID id,
                                                 @PathVariable(value = "relationId") RID relationId,
                                                 @RequestBody R relationObject) {
        GenericRelationUpdatedEvent<T, R, ID, RID> event = genericRelationService.partialUpdateRelationObject(new GenericRelationUpdateEvent<T, R, ID, RID>(id, null, relationId, relationObject));

        R savedObject = event.getRelationObject();

        return new ResponseEntity<R>(savedObject, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = {"/{relationId}" },
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<R> deleteRelationObject(@PathVariable(value = "id") ID id,
                                         @PathVariable(value = "relationId") RID relationId) {
        GenericRelationDeletedEvent<T, R, ID, RID> event = genericRelationService.deleteRelationObject(new GenericRelationDeleteEvent<T, R, ID , RID>(id, null, relationId));

        if (!event.isEntityFound()) {
            return new ResponseEntity<R>(HttpStatus.NOT_FOUND);
        }

        R object = event.getRelationObject();

        if (event.isDeletionCompleted()) {
            return new ResponseEntity<R>(object, HttpStatus.OK);
        }

        return new ResponseEntity<R>(object, HttpStatus.FORBIDDEN);
    }

}
