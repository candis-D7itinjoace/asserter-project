package tn.sharing.asserterproject.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class FieldInValidExceptionHandler{

    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> invalidInputField(MethodArgumentNotValidException exception) {

        // here implement the logic to collect and display the error messages
        Map<String, String> exceptionMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> exceptionMap.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return exceptionMap;
    }

}
