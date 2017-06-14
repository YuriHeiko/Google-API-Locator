package com.heiko.placelocator.parser

class ResponseParserController {
    final ResponseParser parser

    ResponseParserController(ResponseParser parser) {
        this.parser = parser
    }

    def parse(String text) {
        parser.parse(text)
    }
}
