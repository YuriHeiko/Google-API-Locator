package com.heiko.placelocator.utils

/**
 * This is an util class
 */
class URLBuilder {

    /**
     * This function builds a request url to Google Places Web API according to configuration parameters
     *
     * @param config {@code ConfigObject} with configuration parameters
     * @return {@code String} contains a request url
     */
    String getURL(ConfigObject config) {
        return "${config.mainUrl}${config.method}/${config.format}?location=${config.latitude}," +
                "${config.longitude}&radius=${config.radius}&key=${config.authorizationKey}"
    }
}
