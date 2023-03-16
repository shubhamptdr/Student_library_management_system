package com.student.library.management.controllers.advices;

import com.student.library.management.exceptions.AuthorNotFoundException;
import com.student.library.management.exceptions.CardNotFoundException;
import com.student.library.management.exceptions.ResourceAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler({AuthorNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String, Object> handleAuthorNotFoundException(AuthorNotFoundException authorNotFoundException){
        Map<String, Object> map = new HashMap<>();
        map.put("error",authorNotFoundException.getMessage());
        map.put("stacktrace",authorNotFoundException.getStackTrace());
        return map;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, Object> handleException(Exception exception){
        Map<String, Object> map = new HashMap<>();
        map.put("error",exception.getMessage());
        return map;
    }
    @ExceptionHandler({ResourceAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    Map<String, Object> handleException(ResourceAlreadyExistsException exception){
        Map<String, Object> map = new HashMap<>();
        map.put("error",exception.getMessage());
        return map;
    }
    @ExceptionHandler({CardNotFoundException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    Map<String, Object> handleCardNotFoundException(CardNotFoundException cardNotFoundException){
        Map<String, Object> map = new HashMap<>();
        map.put("error",cardNotFoundException.getMessage());
        return map;
    }

}
