//package davidius.authentificationservice.handler;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseEntity<?> handleNotFound(NoHandlerFoundException ex) {
//        Map<String, Object> errorResponse = new HashMap<>();
//        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
//        errorResponse.put("error", "Not Found");
//        errorResponse.put("message", "Aucun endpoint ne correspond à votre requête");
//        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//    }
//}