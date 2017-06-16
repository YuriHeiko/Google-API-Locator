package com.heiko.placelocator.parser

import groovy.json.JsonSlurper

/**
 * Parses and checks the obtained JSON response. Uses the {@link GoogleAPIChecker}
 * trait to check response status
 */
class JSONResponseParser implements ResponseParser,GoogleAPIChecker {

    final JsonSlurper slurper = new JsonSlurper()

    /**
     * Parses and checks the obtained JSON response
     *
     * @param text response in JSON format
     * @return {@code Map} contains parsed response objects
     * @throws {@see GoogleAPIChecker}
     */
    @Override
    Map parse(final String text) {
        checkResponse(slurper.parseText(text) as Map)
    }
}
