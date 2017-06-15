package com.heiko.placelocator.exceptions

class RequestDeniedException extends GoogleAPILocatorException {

    RequestDeniedException(String message) {
        super(message, 'REQUEST_DENIED')
    }

}
