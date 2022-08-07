package communityService.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StatusCode> handleResponseStatusException(ResponseStatusException responseStatusException) {
        StatusCode statusCode = new StatusCode(responseStatusException.getStatus().value(),
                responseStatusException.getReason());
        return new ResponseEntity<>(statusCode, responseStatusException.getStatus());
    }

}
