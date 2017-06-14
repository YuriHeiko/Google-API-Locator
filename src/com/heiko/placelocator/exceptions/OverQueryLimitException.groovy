package com.heiko.placelocator.exceptions

class OverQueryLimitException extends GoogleAPILocatorException {

    OverQueryLimitException(String message) {
        super(message)
    }
}
