package ua.test.escondido.eureka.client.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.test.escondido.eureka.client.entity.Seller;

import java.util.Optional;

public interface SellerRepository extends MongoRepository<Seller, String> {

    Optional<Seller> findByName(String name);
}
