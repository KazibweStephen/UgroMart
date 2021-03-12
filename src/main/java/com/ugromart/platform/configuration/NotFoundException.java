package com.ugromart.platform.configuration;

//If we ever have to use Mongo
//refer to https://docs.mongodb.com/manual/reference/bson-types/
//import org.bson.types.ObjectId;

public class NotFoundException extends  RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Class<?> clazz, long id) {
        super(String.format("Entity %s with id %d not found", clazz.getSimpleName(), id));
    }

    public NotFoundException(Class<?> clazz, String id) {
        super(String.format("Entity %s with id %s not found", clazz.getSimpleName(), id));
    }

    //If we ever have to use mongodb
//    public NotFoundException(Class<?> clazz, ObjectId id) {
//        super(String.format("Entity %s with id %s not found", clazz.getSimpleName(), id.toString()));
//    }
}
