package com.heiko.placelocator.http

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
    HTTPClient get(ConfigObject config) {

        HTTPClient httpClient

        switch (config.HTTPClient) {

            case "Simple":
            default:
                httpClient = new SimpleHTTPClient()
        }

        return httpClient
    }
}
