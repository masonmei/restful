package org.personal.mason.rest.utils;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/25/14
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class RestfulUrlsProvider {
    public static <T> String postUri(Class<T> clazz){
        return "/" + clazz.getSimpleName().toLowerCase();
    }

    public static  <T> String putUri(T entity){
        return  "/" + entity.getClass().getSimpleName().toLowerCase()  + "/{id}";
    }

    public static <T> String getListUri(T entity){
        return "/" + entity.getClass().getSimpleName().toLowerCase();
    }

    public static <T> String getUri(T entity){
        return  "/" + entity.getClass().getSimpleName().toLowerCase()  + "/{id}";
    }

    public static  <T> String patchUri(T entity){
        return  "/" + entity.getClass().getSimpleName().toLowerCase()  + "/{id}";
    }

    public static  <T> String deleteUri(T entity){
        return  "/" + entity.getClass().getSimpleName().toLowerCase()  + "/{id}";
    }

    // relations uri
    public static <T, R> String postRelationUri(T entity, R relation){
        return "/" + entity.getClass().getSimpleName().toLowerCase() + "/{id}/" + relation.getClass().getSimpleName().toLowerCase();
    }

    public static  <T, R> String putRelationUri(T entity, R relation){
        return  "/" + entity.getClass().getSimpleName().toLowerCase()  + "/{id}/" + relation.getClass().getSimpleName().toLowerCase() + "/{rid}";
    }

    public static <T, R> String getRelationListUri(T entity, R relation){
        return "/" + entity.getClass().getSimpleName().toLowerCase() + "/{id}/" + relation.getClass().getSimpleName().toLowerCase();
    }

    public static <T, R> String getRelationUri(T entity, R relation){
        return  "/" + entity.getClass().getSimpleName().toLowerCase()  + "/{id}/" + relation.getClass().getSimpleName().toLowerCase() + "/{rid}";
    }

    public static  <T, R> String patchRelationUri(T entity, R relation){
        return  "/" + entity.getClass().getSimpleName().toLowerCase()  + "/{id}/" + relation.getClass().getSimpleName().toLowerCase() + "/{rid}";
    }

    public static  <T, R> String deleteRelationUri(T entity, R relation){
        return  "/" + entity.getClass().getSimpleName().toLowerCase()  + "/{id}/" + relation.getClass().getSimpleName().toLowerCase() + "/{rid}";
    }
}
