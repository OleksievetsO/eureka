package ua.test.escondido.eureka.client.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.test.escondido.eureka.client.data.PaymentType;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Document(collection = "invoice")
public class Invoice {
    @Id
    private String id;

    private String invoiceNumber;

    private Date issueDate;

    private Date dueDate;

    private PaymentType paymentType;

    @DBRef
    private Seller seller;
    @DBRef
    private Buyer buyer;

    private BigDecimal amount;
}
