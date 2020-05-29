package ua.test.escondido.eureka.client.exception;

import com.mongodb.MongoWriteException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
@RestController
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionDetails> handleBadRequestException(BadRequestException ex, WebRequest request) {
        ExceptionDetails exceptionResponse = new ExceptionDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ExceptionDetails> handleBadParamException(ConstraintViolationException ex, WebRequest request) {
        ExceptionDetails exceptionResponse = new ExceptionDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MongoWriteException.class)
    public final ResponseEntity<ExceptionDetails> handleUniqueException(MongoWriteException ex, WebRequest request) {
        if(ex.getError().getCode() == 11000){
            ExceptionDetails exceptionResponse = new ExceptionDetails(new Date(), ex.getMessage(),
                    request.getDescription(false));
            return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
        }
        ExceptionDetails exceptionResponse = new ExceptionDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> getErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()))
                .findFirst()
                .orElse(ex.getMessage());
        ExceptionDetails exceptionResponse = new ExceptionDetails(new Date(), errorMessage,
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    private String getErrorMessage(String field, String errMessage) {
        return field.concat(": ").concat(errMessage);
    }
}
