package com.intent.tests.dataproviders;

import com.intent.task.models.UserModel;
import net.datafaker.Faker;

public class UsersDP {
    private static final Faker faker = new Faker();

    public static UserModel getDefault() {
        return UserModel.getDefault();
    }
}
