package com.heiko.placelocator.exceptions

/**
 * It is thrown when the attempt to iterate over a finished search was happened
 */
class SearchWasFinishedException extends GoogleAPILocatorException {

    /** Constructs a new {@code SearchWasFinishedException} with the specified detail message
     *
     * @param message the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    SearchWasFinishedException(String message) {
        super(message, 'SEARCH_ERROR')
    }
}
