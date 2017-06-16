package com.heiko.placelocator.http
/**
 * Common interface for HTTP clients implementations
 */
interface HTTPClient {
    /**
     * Sends HTTP Get request to the specified url
     *
     * @param url url for a get request
     * @return a response
     */
    def get(String url)
}
