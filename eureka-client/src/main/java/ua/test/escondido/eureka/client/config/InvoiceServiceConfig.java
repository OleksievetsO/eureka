package ua.test.escondido.eureka.client.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "service")
public class InvoiceServiceConfig {

    @Value("${invoice-service.api.port}")
    private String apiPort;
    private String apiName;
    private String apiDescriptio;
}
