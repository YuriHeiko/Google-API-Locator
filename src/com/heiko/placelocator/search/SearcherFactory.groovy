package com.heiko.placelocator.search

import com.heiko.placelocator.exceptions.GoogleAPILocatorException
import com.heiko.placelocator.google.GoogleAPI
import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.http.URLBuilder
import com.heiko.placelocator.parser.ResponseParser

class SearcherFactory {

    /**
     * Creates the default search algorithm implementation
     *
     * @param config {@code ConfigObject} with configuration parameters
     * @return a {@link TargetPlaceSearcher} object
     * @throws GoogleAPILocatorException if a chosen search algorithm isn't implemented
     */
    SearcherIterator create(GoogleAPI googleAPI, URLBuilder urlBuilder, ConfigObject config) {

        SearcherIterator searcherIterator

        if (config.placeSearcherType == 'Target searcher') {
            searcherIterator = new TargetPlaceSearcher(googleAPI, urlBuilder, config)
        } else {
            throw new GoogleAPILocatorException('The script does not have such a search algorithm ' +
                    "implementation ($config.placeSearcherType)")
        }

        return searcherIterator
    }
}