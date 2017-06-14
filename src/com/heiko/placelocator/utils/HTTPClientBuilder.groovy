package com.heiko.placelocator.utils

import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.http.SimpleHTTPClient

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
     */
    static HTTPClient get(ConfigObject config) {
        final HTTPClient httpClient

        switch (config.HTTPClient) {
            case "Simple":
            default:
                httpClient = new SimpleHTTPClient()
                break
        }

        return httpClient
    }
}
