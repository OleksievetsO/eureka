package ua.test.escondido.eureka.client.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.test.escondido.eureka.client.entity.Buyer;

import java.util.Optional;

public interface BuyerRepository extends MongoRepository<Buyer, String> {
    Optional<Buyer> findByName(String name);

}
