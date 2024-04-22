package org.learning.blogapplication.exceptions;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String resourceName, String fieldname, int fieldvalue) {
        super(resourceName + " not found " + fieldname + " = " + fieldvalue);
    }
}
