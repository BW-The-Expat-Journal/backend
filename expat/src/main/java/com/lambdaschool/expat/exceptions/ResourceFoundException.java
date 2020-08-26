package com.lambdaschool.expat.exceptions;

public class ResourceFoundException
        extends RuntimeException
{
    public ResourceFoundException(String message)
    {
        super("Error from a Lambda School Application " + message);
    }
}
