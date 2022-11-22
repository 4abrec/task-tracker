package tracking.system.auth.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tracking.system.auth.exception.TaskTrackerException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class TaskTrackerExceptionHandler {

    @ExceptionHandler(TaskTrackerException.class)
    public ResponseEntity<ExceptionHandlerStructure> handleException(TaskTrackerException exception,
                                                                     HttpServletRequest webRequest) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(ExceptionHandlerStructure.createException(exception, webRequest.getRequestURI()));
    }
}
