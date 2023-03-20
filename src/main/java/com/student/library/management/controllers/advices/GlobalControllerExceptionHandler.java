package com.student.library.management.controllers.advices;

import com.student.library.management.exceptions.*;
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
    Map<String, Object> handleResourceAlreadyExistsException(ResourceAlreadyExistsException exception){
        Map<String, Object> map = new HashMap<>();
        map.put("error",exception.getMessage());
        return map;
    }
    @ExceptionHandler({CardNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String, Object> handleCardNotFoundException(CardNotFoundException cardNotFoundException){
        Map<String, Object> map = new HashMap<>();
        map.put("error",cardNotFoundException.getMessage());
        return map;
    }
    @ExceptionHandler({StudentNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String, Object> handleStudentNotFoundException(StudentNotFoundException studentNotFoundException){
        Map<String, Object> map = new HashMap<>();
        map.put("error",studentNotFoundException.getMessage());
        return map;
    }
    @ExceptionHandler({BookNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String, Object> handleBookNotFoundException(BookNotFoundException bookNotFoundException){
        Map<String, Object> map = new HashMap<>();
        map.put("error",bookNotFoundException.getMessage());
        return map;
    }

}
