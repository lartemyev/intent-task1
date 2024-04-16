package com.intent.task.objects.api;

import com.google.gson.annotations.SerializedName;
import com.intent.task.objects.api.items.AddressInformationItem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShippingInformationRequest {

    @SerializedName("addressInformation")
    private AddressInformationItem addressInformation;
}
