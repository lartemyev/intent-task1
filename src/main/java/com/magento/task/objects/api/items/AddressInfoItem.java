package com.magento.task.objects.api.items;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class AddressInfoItem {

    @SerializedName("countryId")
    private String countryID;

    @SerializedName("region")
    private String region;

    @SerializedName("street")
    private List<String> street;

    @SerializedName("company")
    private String company;

    @SerializedName("telephone")
    private String telephone;

    @SerializedName("postcode")
    private String postcode;

    @SerializedName("city")
    private String city;

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("saveInAddressBook")
    private Object saveInAddressBook;
}
