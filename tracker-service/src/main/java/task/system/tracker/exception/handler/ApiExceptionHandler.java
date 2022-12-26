package task.system.tracker.exception.handler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import task.system.tracker.exception.ResourceNotFoundException;
import task.system.tracker.exception.dto.ErrorDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ErrorDto(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), e.getMessage(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        log.error(String.valueOf(e));
        return new ResponseEntity<>(new ErrorDto(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), e.getMessage(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorDto> handleEmptyResultDataAccessException(EmptyResultDataAccessException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ErrorDto(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), e.getMessage(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error(e.getMessage());

        List<String> messages = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> String.format("Field [%s] not validated. [%s]"
                        , fieldError.getField()
                        , fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ErrorDto(HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY.name(), messages, request.getRequestURI()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
