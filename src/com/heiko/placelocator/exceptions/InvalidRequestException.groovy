package com.heiko.placelocator.exceptions

/**
 * It is thrown in case of Google places web API return status is INVALID_REQUEST.
 * Normally, it shows that the authorization key is wrong or missed
 */
class InvalidRequestException extends GoogleAPILocatorException {

    /** Constructs a new {@code InvalidRequestException} with the specified detail message
     *
     * @param message the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
   InvalidRequestException(String message) {
        super(message, 'INVALID_REQUEST')
    }
}
