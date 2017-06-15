package com.heiko.placelocator.parser

import com.heiko.placelocator.exceptions.GoogleAPILocatorException

class ParserBuilder {

    /**
     * Creates a {@link ResponseParser} object according to configuration parameters.
     *
     * @param format {@code String} with a format type
     * @return a {@link ResponseParser} object
     * @throws GoogleAPILocatorException if xml format is chosen cause it isn't implemented
     */
    ResponseParser get(final String format) {

        ResponseParser parser

        if (format == 'json') {
            parser = new JSONResponseParser()
        } else {
            throw new GoogleAPILocatorException(/["status": "PARSE_ERROR",/ +
                                                /"cause": "The script doesn't have a parser implementation / +
                                                /for such a format ($format)"]/)
        }

        parser
    }
}
