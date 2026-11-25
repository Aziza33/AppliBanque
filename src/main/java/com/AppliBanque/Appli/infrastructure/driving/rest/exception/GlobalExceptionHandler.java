
package com.AppliBanque.Appli.infrastructure.driving.rest.exception;

// Notre exception métier
import com.AppliBanque.Appli.domain.exception.ClientAlreadyExistsException;

// Notre format de réponse d'erreur
import com.AppliBanque.Appli.infrastructure.driving.rest.dto.ErrorResponse;

// Les outils Spring
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

// Cette annotation dit à Spring :
// "Je m'occupe des erreurs des contrôleurs REST."
@RestControllerAdvice
public class GlobalExceptionHandler
        extends ResponseEntityExceptionHandler {

    // ========================================
    // 1. CLIENT DÉJÀ EXISTANT : 409
    // ========================================

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleClientExists(
            ClientAlreadyExistsException exception
    ) {

        // On prépare une fiche expliquant l'erreur.
        ErrorResponse error = new ErrorResponse(
                409,
                "Conflict",
                exception.getMessage(),
                LocalDateTime.now()
        );

        // On retourne HTTP 409 avec notre fiche.
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    // ========================================
    // 2. DONNÉES INVALIDES : 400
    // ========================================

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {

        // On récupère le premier message
        // de validation rencontré.
        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Données invalides");

        ErrorResponse error = new ErrorResponse(
                400,
                "Bad Request",
                message,
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    // ========================================
    // 3. MAUVAISE MÉTHODE HTTP : 405
    // ========================================

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {

        ErrorResponse error = new ErrorResponse(
                405,
                "Method Not Allowed",
                "Cette méthode HTTP n'est pas autorisée",
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .headers(headers)
                .body(error);
    }

    // ========================================
    // 4. RESSOURCE INTROUVABLE : 404
    // ========================================

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(
            NoResourceFoundException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {

        ErrorResponse error = new ErrorResponse(
                404,
                "Not Found",
                "La ressource demandée est introuvable",
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    // ========================================
    // 5. AUTRES ERREURS SPRING
    // ========================================

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception exception,
            Object body,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {

        ErrorResponse error = new ErrorResponse(
                status.value(),
                HttpStatus.resolve(status.value()) != null
                        ? HttpStatus.resolve(status.value()).getReasonPhrase()
                        : "HTTP Error",
                "La requête n'a pas pu être traitée",
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(error);
    }

    // ========================================
    // 6. ERREURS INATTENDUES : 500
    // ========================================

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(
            Exception exception
    ) {

        ErrorResponse error = new ErrorResponse(
                500,
                "Internal Server Error",
                "Une erreur interne est survenue",
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
