package task.system.tracker.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PageSizeValidator {
    @Value("${task-tracker-config.get-page-config.default-size}")
    private Integer defaultPageSize;
    @Value("${task-tracker-config.get-page-config.max-size}")
    private Integer maxPageSize;

    public Integer getPageSize(Integer pageSize) {
        if(pageSize == null || pageSize == 0) {
            pageSize = defaultPageSize;
        }

        if(pageSize > maxPageSize) {
            pageSize = maxPageSize;
        }

        return pageSize;
    }

}
