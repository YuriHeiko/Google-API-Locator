package com.heiko.placelocator.exceptions

/**
 * {@code GoogleAPILocatorException} is the superclass of those
 * exceptions that can be thrown during the normal operation of the
 * Locator script
 */
class GoogleAPILocatorException extends RuntimeException {

    private String errorCode = 'SCRIPT_ERROR'

    /** Constructs a new {@code GoogleAPILocatorException} with the specified detail message.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    GoogleAPILocatorException(String message) {
        super(message)
    }

    /** Constructs a new {@code GoogleAPILocatorException} with the specified detail message
     * and the {@link #errorCode}
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     * @param   {@link #errorCode} the specified error code. The code is saved for for
     *          later retrieval by the {@link #getErrorCode()} method.
     */
    GoogleAPILocatorException(String message, String errorCode) {
        this(message)
        this.errorCode = errorCode
    }

    /**
     * Returns the error code
     *
     * @return  {@link #errorCode}
     */
    String getErrorCode() {
        errorCode
    }
}