package ua.test.escondido.eureka.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.test.escondido.eureka.client.data.request.InvoiceRequest;
import ua.test.escondido.eureka.client.entity.Buyer;
import ua.test.escondido.eureka.client.entity.Invoice;
import ua.test.escondido.eureka.client.entity.Seller;
import ua.test.escondido.eureka.client.exception.BadRequestException;
import ua.test.escondido.eureka.client.repository.BuyerRepository;
import ua.test.escondido.eureka.client.repository.SellerRepository;
import ua.test.escondido.eureka.client.repository.InvoiceRepository;
import ua.test.escondido.eureka.client.service.InvoiceService;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, SellerRepository sellerRepository, BuyerRepository buyerRepository) {
        this.invoiceRepository = invoiceRepository;
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
    }

    @Override
    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getById(String invoiceId) {
        return invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new BadRequestException(String.format("Invoice with ID %s not found!", invoiceId)));
    }

    @Override
    public void save(InvoiceRequest invoiceRequest) {
        Invoice invoice = new Invoice();
        setupInvoice(invoiceRequest, invoice);
        invoiceRepository.save(invoice);
    }



    @Override
    public void update(String invoiceId, InvoiceRequest invoiceRequest) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new BadRequestException(String.format("Invoice with ID %s not found!", invoiceId)));
        setupInvoice(invoiceRequest, invoice);
        invoiceRepository.save(invoice);
    }

    @Override
    public void delete(String invoiceId) {
        invoiceRepository.deleteById(invoiceId);
    }

    private void setupInvoice(InvoiceRequest invoiceRequest, Invoice invoice) {
        Seller seller = invoiceRequest.getSeller();
        Seller savedSeller = sellerRepository.findByName(seller.getName()).orElseGet(null);
        if (savedSeller == null) {
            savedSeller = new Seller();
        }
        savedSeller.setAddress(seller.getAddress());
        savedSeller.setCity(seller.getCity());
        savedSeller.setName(seller.getName());
        savedSeller.setVatId(seller.getVatId());
        savedSeller.setZipCode(seller.getZipCode());
        Seller sellerInDB = sellerRepository.save(savedSeller);

        Buyer buyer = invoiceRequest.getBuyer();
        Buyer savedBuyer = buyerRepository.findByName(buyer.getName()).orElseGet(null);
        if (savedBuyer == null) {
            savedBuyer = new Buyer();
        }
        savedBuyer.setAddress(buyer.getAddress());
        savedBuyer.setCity(buyer.getCity());
        savedBuyer.setName(buyer.getName());
        savedBuyer.setVatId(buyer.getVatId());
        savedBuyer.setZipCode(buyer.getZipCode());
        Buyer buyerInDB = buyerRepository.save(buyer);


        invoice.setInvoiceNumber(invoiceRequest.getInvoiceNumber());
        invoice.setIssueDate(invoiceRequest.getIssueDate());
        invoice.setDueDate(invoiceRequest.getDueDate());
        invoice.setPaymentType(invoiceRequest.getPaymentType());
        invoice.setSeller(sellerInDB);
        invoice.setBuyer(buyerInDB);
    }
}
