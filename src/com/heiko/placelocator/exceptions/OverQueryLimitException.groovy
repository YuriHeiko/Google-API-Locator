package com.heiko.placelocator.exceptions

/**
 * It is thrown in case of Google places web API return status is OVER_QUERY_LIMIT.
 * It says that account linked with the authorization key is reached the API query limit
 */
class OverQueryLimitException extends GoogleAPILocatorException {

    /** Constructs a new {@code OverQueryLimitException} with the specified detail message
     *
     * @param message the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    OverQueryLimitException(String message) {
        super(message, 'OVER_QUERY_LIMIT')
    }
}
