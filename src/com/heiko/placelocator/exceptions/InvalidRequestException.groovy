package com.heiko.placelocator.exceptions

class InvalidRequestException extends GoogleAPILocatorException {
    InvalidRequestException(String message) {
        super(message)
    }
}
