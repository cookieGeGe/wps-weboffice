package io.github.cookiegege.wpsOffice.exception;

import io.github.cookiegege.wpsOffice.response.WpsErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author JoSuper
 * @date 2026/1/13 16:02
 */
@ControllerAdvice
public class WpsGlobalExceptionHandler {

    @ExceptionHandler(WpsCallbackException.class)
    public ResponseEntity<WpsErrorResponse> handleWpsCallbackException(WpsCallbackException ex) {
        WpsErrorResponse response = new WpsErrorResponse(
                ex.getCode(),
                ex.getMessage(),
                ex.getMessage(),
                ex.getHint()
        );
        return ResponseEntity.status(200).body(response);
    }

    @ExceptionHandler(WpsWebOfficeException.class)
    public ResponseEntity<WpsErrorResponse> handleWpsWebOfficeException(Exception ex) {
        ex.printStackTrace();
        WpsErrorResponse response = new WpsErrorResponse(
                50001,
                "ServerError",
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(200).body(response);
    }

    @ExceptionHandler(WpsApiException.class)
    public ResponseEntity<WpsErrorResponse> handleWpsApiException(Exception ex) {
        ex.printStackTrace();
        WpsErrorResponse response = new WpsErrorResponse(
                50001,
                "ServerError",
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(200).body(response);
    }

}
