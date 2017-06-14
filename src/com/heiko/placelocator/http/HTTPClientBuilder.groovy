package com.heiko.placelocator.http

import com.heiko.placelocator.exceptions.GoogleAPILocatorException

/**
 * This is an util class
 */
class HTTPClientBuilder {

    /**
     * Creates and returns a new HTTPClient which version is
     * based on the configuration parameter - 'HTTPClient'.
     *
     * @param config {@code ConfigObject} with configuration parameters
     * @return a HTTPClient object
     * @throws GoogleAPILocatorException if a chosen HTTPClient isn't implemented
     */
    HTTPClient get(ConfigObject config) {

        final HTTPClient httpClient

        if (config.HTTPClient == 'simple') {
            httpClient = new SimpleHTTPClient()
        } else {
            throw new GoogleAPILocatorException("The script doesn't have such a HTTP-client implementation ($config.HTTPClient)")
        }

        return httpClient
    }
}
