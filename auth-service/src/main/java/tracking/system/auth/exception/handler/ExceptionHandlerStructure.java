package tracking.system.auth.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import tracking.system.auth.exception.TaskTrackerException;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionHandlerStructure {

    private int status;
    private String error;
    private String message;
    private Date date;
    private String URL;

    public static ExceptionHandlerStructure createException(TaskTrackerException exception, String path) {
        return new ExceptionHandlerStructure(exception.getHttpStatus().value(), exception.getHttpStatus().name(),
                exception.getMessage(), new Date(System.currentTimeMillis()), path);
    }
}
