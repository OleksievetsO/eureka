package ua.test.escondido.eureka.client.entity;

import com.mongodb.lang.NonNull;
import ua.test.escondido.eureka.client.data.AccountType;

public class Seller extends Account {

    private AccountType type = AccountType.SELLER;
    @NonNull
    private String bankName;
    @NonNull
    private String iban;
    @NonNull
    private String swift;
}
