package com.heiko.placelocator.search

import com.heiko.placelocator.exceptions.GoogleAPILocatorException
import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.http.URLBuilder
import com.heiko.placelocator.parser.ResponseParser

class PlaceSearcherFactory {

    /**
     * Creates the default search algorithm implementation
     *
     * @param config {@code ConfigObject} with configuration parameters
     * @return a {@link TargetPlaceSearcher} object
     * @throws GoogleAPILocatorException if a chosen search algorithm isn't implemented
     */
    SearcherIterator create(ResponseParser responseParser, HTTPClient httpClient,
                            URLBuilder urlBuilder, ConfigObject config) {

        SearcherIterator searcherIterator

        if (config.placeSearcherType == 'Target searcher') {
            searcherIterator = new TargetPlaceSearcher(urlBuilder, httpClient, responseParser, config)
        } else {
            throw new GoogleAPILocatorException("The script doesn't have such a search algorithm" +
                    " implementation ($config.placeSearcherType)")
        }

        return searcherIterator
    }
}