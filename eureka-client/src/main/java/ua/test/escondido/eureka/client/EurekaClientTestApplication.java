package ua.test.escondido.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.retry.annotation.EnableRetry;

@EnableCircuitBreaker
@EnableRetry
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientTestApplication.class, args);
	}

}
