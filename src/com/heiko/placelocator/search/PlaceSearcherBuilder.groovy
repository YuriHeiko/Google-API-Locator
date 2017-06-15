package com.heiko.placelocator.search

import com.heiko.placelocator.exceptions.GoogleAPILocatorException
import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.http.URLBuilder
import com.heiko.placelocator.parser.ResponseParser

class PlaceSearcherBuilder {

    /**
     * Creates the default search algorithm implementation
     *
     * @param config {@code ConfigObject} with configuration parameters
     * @return a {@link PlaceSearcher} object
     * @throws GoogleAPILocatorException if a chosen search algorithm isn't implemented
     */
    Searcher get(ResponseParser responseParser, HTTPClient httpClient,
                 URLBuilder urlBuilder, ConfigObject config) {

        Searcher searcher

        if (config.placeSearcher == 'new') {
            searcher = new PlaceSearcher(responseParser, httpClient, urlBuilder, config)
        } else {
            throw new GoogleAPILocatorException("The script doesn't have such a search algorithm" +
                    " implementation ($config.PlaceSearcher)")
        }

        return searcher
    }
}