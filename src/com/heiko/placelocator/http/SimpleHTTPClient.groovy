package com.heiko.placelocator.http

class SimpleHTTPClient implements HTTPClient {

    @Override
    def get(final String url) {
        url.toURL().getText()
    }
}
