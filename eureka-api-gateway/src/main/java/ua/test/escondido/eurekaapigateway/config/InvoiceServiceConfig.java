package ua.test.escondido.eurekaapigateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "invoice-service")
public class InvoiceServiceConfig {

    private String apiPort;
    private String apiName;
    private String apiDescriptio;
}
