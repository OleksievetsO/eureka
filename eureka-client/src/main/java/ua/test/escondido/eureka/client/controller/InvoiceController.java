package ua.test.escondido.eureka.client.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.test.escondido.eureka.client.config.InvoiceServiceConfig;
import ua.test.escondido.eureka.client.data.request.InvoiceRequest;
import ua.test.escondido.eureka.client.entity.Invoice;
import ua.test.escondido.eureka.client.service.InvoiceService;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private InvoiceServiceConfig config;

    @RequestMapping(value = "", method = POST)
    @Transactional
    public ResponseEntity addInvoice(@RequestBody InvoiceRequest invoiceRequest) {
        invoiceService.save(invoiceRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{invoiceId}", method = PUT)
    @Transactional
    public ResponseEntity updateInvoice(@RequestBody InvoiceRequest invoiceRequest,
                                     @PathVariable String invoiceId) {
        invoiceService.update(invoiceId, invoiceRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "", method = GET)
    @Transactional
    public ResponseEntity<ObjectNode> getAll(HttpServletRequest httpRequest) {
        Assert.notNull(config);
        ObjectNode result = objectMapper.createObjectNode()
                .putPOJO("template", invoiceService.getAll());
        result.put("Instance", httpRequest.getServerPort());
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/{invoiceId}", method = GET)
    @Transactional
    public ResponseEntity<Invoice> getById(@PathVariable String invoiceId) {
        return new ResponseEntity<>(invoiceService.getById(invoiceId),HttpStatus.OK);
    }

    @RequestMapping(value = "/{invoiceId}", method = DELETE)
    @Transactional
    public ResponseEntity deleteInvoice(@PathVariable String invoiceId) {
        invoiceService.delete(invoiceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
