package com.heiko.placelocator.http

import java.util.stream.Collectors

class URLBuilder {

    private final String urlPrefix
    private Map urlOptions

    URLBuilder(Map urlOptions, String urlPrefix) {
        this.urlPrefix = urlPrefix
        this.urlOptions = urlOptions
    }

    boolean setOption(String key, def value) {
        urlOptions.put(key, value)
    }

    String getOption(final String key) {
        urlOptions.get(key)
    }

    String get() {
        new StringBuilder(urlPrefix) <<
                urlOptions.
                        keySet().
                        stream().
                        map({ k -> k + '=' + urlOptions.get(k) }).
                        collect(Collectors.joining('&'))
    }
}
