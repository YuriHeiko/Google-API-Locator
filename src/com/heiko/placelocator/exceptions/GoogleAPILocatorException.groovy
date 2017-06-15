package com.heiko.placelocator.exceptions

class GoogleAPILocatorException extends RuntimeException {

    String errorCode = 'SCRIPT_ERROR'

    GoogleAPILocatorException(String message) {
        super(message)
    }

    GoogleAPILocatorException(String message, String errorCode) {
        this(message)
        this.errorCode = errorCode
    }

    String getErrorCode() {
        errorCode
    }
}