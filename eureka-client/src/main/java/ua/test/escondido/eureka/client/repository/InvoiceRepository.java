package ua.test.escondido.eureka.client.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.test.escondido.eureka.client.entity.Invoice;

import java.util.Optional;

public interface InvoiceRepository extends MongoRepository<Invoice, String> {

    Optional<Invoice> findByInvoiceNumber(String invoiceNo);
}
