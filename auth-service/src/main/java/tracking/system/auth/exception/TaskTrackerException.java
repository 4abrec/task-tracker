package tracking.system.auth.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class TaskTrackerException extends RuntimeException{

    private HttpStatus httpStatus;

    public TaskTrackerException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public TaskTrackerException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public TaskTrackerException(Throwable cause) {
        super(cause);
    }
}
