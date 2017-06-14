package com.heiko.placelocator.parser

import com.heiko.placelocator.exceptions.GoogleAPILocatorException

/**
 * This is an util class
 */
class ParserBuilder {

    /**
     * Creates a {@link ResponseParser} object according to configuration parameters.
     *
     * @param format {@code String} with a format type
     * @return a {@link ResponseParser} object
     * @throws GoogleAPILocatorException if xml format is chosen cause it isn't implemented
     */
    ResponseParser get(String format) {
        final ResponseParser parser

        if (format == 'json') {
            parser = new JSONResponseParser()
        } else {
            throw new GoogleAPILocatorException("The script doesn't have a parser implementation for such a format ($format)")
        }

        return parser
    }
}
