package com.heiko.placelocator.http

import java.util.stream.Collectors

/**
 *
 */
class URLBuilder {

    private final String[] urlPrefix
    private Map urlOptions

    URLBuilder(Map urlOptions, String... urlPrefix) {
        this.urlPrefix = urlPrefix
        this.urlOptions = urlOptions
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    boolean changeOption(String key, def value) {

        boolean result = false

        // TODO: rewrite
        try {
            result = urlOptions.put(key, value)
        } catch (Exception e) {
            println e.getMessage()
            result = false
        }

        return result
    }

    /**
     *
     * @param key
     * @return
     */
    def getOption(final String key) {
        urlOptions.get(key)
    }

    /**
     * Builds a request url to Google Places Web API
     *
     * @return {@code String} contains a request url
     */
    String get() {

        StringBuilder builder = new StringBuilder(
                new ArrayList<String>(Arrays.asList(urlPrefix)).
                        stream().
                        collect(Collectors.joining('/')))

        builder << '?'

        builder << urlOptions.
                keySet().
                stream().
                map({k -> k + '=' + urlOptions.get(k)}).
                collect(Collectors.joining('&'))

        return builder
    }
}
