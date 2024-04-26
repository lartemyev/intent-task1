package com.magento.task.objects.api.items;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class AddressInformationItem {

    @SerializedName("shipping_address")
    private AddressInfoItem shippingAddress;

    @SerializedName("billing_address")
    private AddressInfoItem billingAddress;

    @SerializedName("shipping_method_code")
    private String shippingMethodCode;

    @SerializedName("shipping_carrier_code")
    private String shippingCarrierCode;
}
