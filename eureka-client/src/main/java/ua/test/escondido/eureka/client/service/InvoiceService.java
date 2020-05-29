package ua.test.escondido.eureka.client.service;

import ua.test.escondido.eureka.client.data.request.InvoiceRequest;
import ua.test.escondido.eureka.client.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    List<Invoice> getAll();

    Invoice getById(String invoiceId);

    void save(InvoiceRequest invoice);

    void update(String invoiceId, InvoiceRequest invoice);

    void delete(String invoiceId);
}
