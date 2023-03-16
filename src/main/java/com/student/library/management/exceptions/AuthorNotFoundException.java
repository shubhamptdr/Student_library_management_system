package com.student.library.management.exceptions;

public class AuthorNotFoundException extends Exception{

    public AuthorNotFoundException(String authorName) {
        super("No such author: "+ authorName);
    }
}
