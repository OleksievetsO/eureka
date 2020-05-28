package ua.test.escondido.eureka.client.data.request;

import lombok.Getter;
import lombok.Setter;
import ua.test.escondido.eureka.client.data.PaymentType;
import ua.test.escondido.eureka.client.entity.Buyer;
import ua.test.escondido.eureka.client.entity.Seller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class InvoiceRequest {
    @NotNull
    @NotEmpty
    private String invoiceNumber;
    @NotNull
    private Date issueDate;
    @NotNull
    private Date dueDate;
    @NotNull
    private PaymentType paymentType;
    @NotNull
    private Seller seller;
    @NotNull
    private Buyer buyer;
    @NotNull
    private BigDecimal amount;
}
