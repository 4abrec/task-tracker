package task.system.tracker.exception.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class ErrorDto {

    private int status;
    private String error;
    private List<String> messages;
    private LocalDateTime timestamp;
    private String url;

    public ErrorDto(int status, String error, List<String> messages, String url) {
        this.status = status;
        this.error = error;
        this.messages = messages;
        this.timestamp = LocalDateTime.now();
        this.url = url;
    }
    public ErrorDto(int status, String error, String message, String url) {
        this.status = status;
        this.error = error;
        this.messages = Collections.singletonList(message);
        this.timestamp = LocalDateTime.now();
        this.url = url;
    }


}
