package com.example.bootcamp.exceptions;

import com.example.bootcamp.util.MessageUtils;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super(MessageUtils.NO_RECORDS_FOUND);
    }
}
