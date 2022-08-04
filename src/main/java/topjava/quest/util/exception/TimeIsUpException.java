package topjava.quest.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED, reason = """
        "You can't vote anymore today. " +
                            "After 11 am, come back tomorrow. " +
                            "You must to do it until 11.\"""")
public class TimeIsUpException extends RuntimeException {
    public TimeIsUpException(String message) {
        super(message);
    }
}
