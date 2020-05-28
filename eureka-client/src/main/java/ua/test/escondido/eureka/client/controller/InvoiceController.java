package ua.test.escondido.eureka.client.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.test.escondido.eureka.client.data.request.InvoiceRequest;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @RequestMapping(value = "", method = POST)
    @Transactional
    public ResponseEntity addInvoice(@RequestBody InvoiceRequest invoiceRequest) {
        //Principal principal = principalService.addPrincipal(data.get("id").toString());
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }
}
