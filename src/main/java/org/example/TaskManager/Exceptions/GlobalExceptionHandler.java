package org.example.TaskManager.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.example.TaskManager.Dto.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //handling controller exceptions

    //yha handle hogi tasknot found jo controller throw karra hain after getting it from service
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlingTaskNotFound(TaskNotFoundException ex){
        ErrorResponse err = new ErrorResponse(
                "TaskNotFound",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }


    @ExceptionHandler(InvalidStatusException.class)
    public ResponseEntity<ErrorResponse> handleInvalidStatus(InvalidStatusException ex) {
        ErrorResponse err = new ErrorResponse(
                "InvalidStatusError",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}

//sbse pehle task not found times exception define like super(ex.message)
//if not defined yha global handler main karo
//return normal message or error response object proper details return
