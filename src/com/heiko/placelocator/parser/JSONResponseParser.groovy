package com.heiko.placelocator.parser

import groovy.json.JsonSlurper

class JSONResponseParser implements ResponseParser {

    final JsonSlurper slurper = new JsonSlurper()

    @Override
    def parse(final String text) {
        slurper.parseText text
    }
}
