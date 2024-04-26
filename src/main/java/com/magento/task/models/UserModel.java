package com.magento.task.models;

import lombok.Builder;
import lombok.Data;
import net.datafaker.Faker;

@Builder
@Data
public class UserModel {

    private String email;
    private String firstName;
    private String lastName;
    private String company;
    private String streetAddress;
    private String city;
    private String postCode;
    private String country;
    private String phoneNumber;

    private static Faker faker = new Faker();

    public static UserModel getDefault() {
        return UserModel.builder()
                .email("noam@gmail.com")
                .firstName("Noam")
                .lastName("Ben Ishay")
                .company("IntentIQ")
                .streetAddress("Nof Harim, 126")
                .city("Tzur Yigal")
                .postCode("4486200")
                .country("Israel")
                .phoneNumber("0522867553")
                .build();
    }
}
