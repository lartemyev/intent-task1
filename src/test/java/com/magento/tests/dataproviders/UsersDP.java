package com.magento.tests.dataproviders;

import com.magento.task.models.UserModel;
import net.datafaker.Faker;

public class UsersDP {
    private static final Faker faker = new Faker();

    public static UserModel getDefault() {
        return UserModel.getDefault();
    }
}
