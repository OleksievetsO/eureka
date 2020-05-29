package ua.test.escondido.eureka.client.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
class ExceptionDetails {
    private Date timestamp;
    private String error;
    private String details;

}
