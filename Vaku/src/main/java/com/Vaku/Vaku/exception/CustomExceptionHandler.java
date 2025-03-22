package com.Vaku.Vaku.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestControllerAdvice
public class CustomExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // 🔹 Extraer solo los mensajes de error sin incluir los nombres de los campos
        String mensajesErrores = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage()) // Solo el mensaje de error
                .distinct() // Evitar mensajes duplicados
                .reduce((msg1, msg2) -> msg1 + ", " + msg2) // Concatenar mensajes separados por ", "
                .orElse("Error de validación"); // Si no hay mensajes, usar este por defecto

        Map<String, Object> response = new HashMap<>();
        response.put("date", LocalDate.now().toString());
        response.put("message", mensajesErrores);
        response.put("errorCode", 400);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("date", LocalDate.now().toString());
        response.put("errorCode", 500); // Mantener código 500

        // Si es un error de validación
        if (ex instanceof MethodArgumentNotValidException) {
            String mensajesErrores = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors().stream()
                    .map(error -> error.getDefaultMessage()) // Extrae solo el mensaje sin el campo
                    .distinct()
                    .reduce((msg1, msg2) -> msg1 + ", " + msg2) // Une mensajes separados por ", "
                    .orElse("Error de validación");

            response.put("message", mensajesErrores);
        } else {
            // Para otras excepciones, mostrar solo el mensaje sin detalles adicionales
            response.put("message", limpiarMensaje(ex.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * Limpia el mensaje eliminando cualquier prefijo antes de los ":"
     */
    private String limpiarMensaje(String mensaje) {
        if (mensaje == null) return "Ocurrió un error inesperado";
        return mensaje.replaceAll(".*?: ", ""); // Elimina todo antes del primer ": "
    }


    // Manejo de NotFoundException (404)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("date", LocalDate.now().toString());
        response.put("message", ex.getMessage());
        response.put("errorCode", 404);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }









//    APARTE
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<Object> handleNotFoundException(NotFoundException notFoundException){
//        Map<String, String> response = new HashMap<>();
//        response.put("Date: ", LocalDate.now().toString());
//        response.put("Message: ",notFoundException.getMessage());
//        response.put("Error Code: ", "404");
//        return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
//    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
//
//    @ExceptionHandler(AlreadyExistsException.class)
//    public ResponseEntity<Object> handleAlreadyExistsException(AlreadyExistsException ex) {
//        Map<String, String> response = new HashMap<>();
//        response.put("Date: ", LocalDate.now().toString());
//        response.put("Message: ", ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
//    }
//
//    @ExceptionHandler(AuthenticationFailedException.class)
//    public ResponseEntity<?> handleAuthenticationFailedException(AuthenticationFailedException ex) {
//        Map<String, Object> body = new HashMap<>();
//        body.put("timestamp", LocalDate.now().toString());
//        body.put("message", ex.getMessage());
//        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
//    }
//
//    @ExceptionHandler(BindException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(BindException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
//        return ResponseEntity.badRequest().body(errors);
//    }
}