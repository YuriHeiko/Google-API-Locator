package com.heiko.placelocator.utils

import com.heiko.placelocator.exceptions.GoogleAPILocatorException
import com.heiko.placelocator.parser.JSONResponseParser
import com.heiko.placelocator.parser.ResponseParser

/**
 * This is an util class
 */
class ParserBuilder {

    /**
     * This function creates a {@code ResponseParser} object according to configuration parameters.
     *
     * @param config {@code ConfigObject} with configuration parameters
     * @return specified format parser
     * @throws GoogleAPILocatorException if xml format is chosen cause it isn't implemented
     */
    static ResponseParser get(ConfigObject config) {
        final ResponseParser parser

        switch (config.format) {
            case 'json':
                parser = new JSONResponseParser()
                return parser
            case 'xml':
            default:
                throw new GoogleAPILocatorException('XML parser is not implemented')
        }
    }
}
