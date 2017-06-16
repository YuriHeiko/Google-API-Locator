package com.heiko.placelocator.exceptions

/**
 * It is thrown in case of Google places web API return status is INVALID_REQUEST.
 * Normally, it says that some properties are wrong or missed
 */
class RequestDeniedException extends GoogleAPILocatorException {

    RequestDeniedException(String message) {
        super(message, 'REQUEST_DENIED')
    }

}
