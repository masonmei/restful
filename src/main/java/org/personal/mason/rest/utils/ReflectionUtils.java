package org.personal.mason.rest.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/25/14
 * Time: 2:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReflectionUtils {
    public static Type[] getParameterizedType(Object object){
        Type superClassType = object.getClass().getGenericSuperclass();
        if(!ParameterizedType.class.isAssignableFrom(object.getClass())){
            return null;
        }
        return ((ParameterizedType)superClassType).getActualTypeArguments();
    }

    public static void main(String ... args){
        GenericClazz<Integer, String, Double> genericClazz = new GenericClazz<Integer, String, Double>();
        Type[] types = ReflectionUtils.getParameterizedType(GenericClazz.class);
        Integer i = null;
    }
}

class GenericClazz<T,E,R> {

}
