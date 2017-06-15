package com.heiko.placelocator.parser

import com.heiko.placelocator.exceptions.GoogleAPILocatorException

class ParserFactory {

    /**
     * Creates a {@link ResponseParser} object according to configuration parameters.
     *
     * @param dataFormat {@code String} with a format type
     * @return a {@link ResponseParser} object
     * @throws GoogleAPILocatorException if xml format is chosen cause it isn't implemented
     */
    ResponseParser create(final String dataFormat) {

        ResponseParser parser

        if (dataFormat == 'json') {
            parser = new JSONResponseParser()
        } else {
            throw new GoogleAPILocatorException('The script does not have a parser implementation ' +
                                                "for such a format ($dataFormat)")
        }

        parser
    }
}
