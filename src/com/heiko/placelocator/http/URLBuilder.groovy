package com.heiko.placelocator.http

import java.util.stream.Collectors

/**
 * Builds the url according initial parametrs
 */
class URLBuilder {

    private final String urlPrefix
    private Map urlOptions

    /**
     * Constructs the object
     *
     * @param urlOptions
     * @param urlPrefix
     */
    URLBuilder(Map urlOptions, String urlPrefix) {
        this.urlPrefix = urlPrefix
        this.urlOptions = urlOptions
    }

    /**
     * Sets the new value associated with key
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return true if value is put
     */
    boolean setOption(String key, def value) {
        urlOptions.put(key, value)
    }

    /**
     * Returns the value associated with the key
     *
     * @param key key with which the specified value is to be associated
     * @return the associated value
     */
    def getOption(final String key) {
        urlOptions.get(key)
    }

    /**
     * Builds and returns the url according to urlOptions
     *
     * @return string contains the built url
     */
    String get() {
        new StringBuilder(urlPrefix) <<
                urlOptions.
                        keySet().
                        stream().
                        map({ k -> k + '=' + urlOptions.get(k) }).
                        collect(Collectors.joining('&'))
    }

    // TODO:
    /**
     *
     *
     * @return string contains the built url
     */
    String get(int newRadius) {
        setOption("radius", newRadius)

        new StringBuilder(urlPrefix) <<
                urlOptions.
                        keySet().
                        stream().
                        map({ k -> k + '=' + urlOptions.get(k) }).
                        collect(Collectors.joining('&'))
    }
}