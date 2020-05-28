package ua.test.escondido.eureka.client.entity;

import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Document(collection = "account")
public class Account {
    @Id
    private String id;
    @NonNull
    @Indexed(unique = true)
    private String name;
    @NonNull
    private String address;
    @NonNull
    private String city;
    @NonNull
    private String zipCode;
    @NonNull
    private String vatId;

}
