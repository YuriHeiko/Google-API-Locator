package com.heiko.placelocator.http

class HTTPClientController {

    final HTTPClient httpClient

    HTTPClientController(HTTPClient httpClient) {
        this.httpClient = httpClient
    }

    def get(final String url) {
        httpClient.get(url)
    }
}
