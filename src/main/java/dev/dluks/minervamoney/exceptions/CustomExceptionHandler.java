package dev.dluks.minervamoney.exceptions;

import dev.dluks.minervamoney.dtos.CustomErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.logging.Logger;

@RestControllerAdvice
public class CustomExceptionHandler {

    // TODO: ENVIAR PARA OBSERVABILIDADE
    Logger logger = Logger.getLogger(CustomExceptionHandler.class.getName());

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<CustomErrorResponse> handleUserAlreadyExistsException(
            UserAlreadyExistsException e,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorResponse error = createErrorResponse(e.getMessage(), request, status);
        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<CustomErrorResponse> handleInvalidCredentialsException(
            InvalidCredentialsException e,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorResponse error = createErrorResponse(e.getMessage(), request, status);
        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<CustomErrorResponse> handleMalformedJwtException(
            MalformedJwtException e,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorResponse error = createErrorResponse("Invalid token", request, status);
        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<CustomErrorResponse> handleSignatureException(
            SignatureException e,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorResponse error = createErrorResponse("Invalid token", request, status);
        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<CustomErrorResponse> handleExpiredJwtException(
            ExpiredJwtException e,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorResponse error = createErrorResponse("Expired token", request, status);
        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleException(
            Exception e,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        CustomErrorResponse error = createErrorResponse(e.getMessage(), request, status);

        e.printStackTrace();
        logger.severe(e.getMessage());

        return ResponseEntity.status(status).body(error);

    }

    private static CustomErrorResponse createErrorResponse(String e, HttpServletRequest request, HttpStatus status) {
        return CustomErrorResponse.builder()
                .timestamp(Instant.now())
                .error(e)
                .path(request.getRequestURI())
                .status(status.value())
                .build();
    }

}
