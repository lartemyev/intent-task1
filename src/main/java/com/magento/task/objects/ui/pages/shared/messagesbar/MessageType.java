package com.magento.task.objects.ui.pages.shared.messagesbar;

import lombok.Getter;

import java.util.Random;

@Getter
public enum MessageType {
    UNKNOWN("unknown"),
    ERROR_MESSAGE("error message"),
    SUCCESS_MESSAGE("success message");

    private final String name;
    MessageType(String name) {
        this.name = name;
    }
}
