package com.heiko.placelocator.http

/**
 * Uses builtin Groovy HTTP client
 */
class SimpleHTTPClient implements HTTPClient {

    /**
     * Sends HTTP Get request to the specified url
     *
     * @param url url for a get request
     * @return a response
     */
    @Override
    def get(final String url) {
        url.toURL().getText() // can throw IOException ??
    }
}
