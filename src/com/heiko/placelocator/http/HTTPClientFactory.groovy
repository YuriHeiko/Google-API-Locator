package com.heiko.placelocator.http

import com.heiko.placelocator.exceptions.GoogleAPILocatorException

class HTTPClientFactory {

    /**
     * Creates and returns a new HTTPClient which version is
     * based on the configuration parameter - 'HTTPClient'.
     *
     * @param clientType {@code String} with a HTTP client type
     * @return a {@link HTTPClient} object
     * @throws GoogleAPILocatorException if a chosen HTTPClient isn't implemented
     */
    HTTPClient get(final String clientType) {

        final HTTPClient httpClient

        if (clientType == 'simple') {
            httpClient = new SimpleHTTPClient()
        } else {
            throw new GoogleAPILocatorException("The script doesn't have such a HTTP-client implementation ($clientType)")
        }

        return httpClient
    }
}
