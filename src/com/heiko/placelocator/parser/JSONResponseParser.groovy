package com.heiko.placelocator.parser

import groovy.json.JsonSlurper

class JSONResponseParser implements ResponseParser,GoogleAPIChecker {

    final JsonSlurper slurper = new JsonSlurper()

    @Override
    Map parse(final String text) {
        checkResponse(slurper.parseText(text) as Map)
    }
}
