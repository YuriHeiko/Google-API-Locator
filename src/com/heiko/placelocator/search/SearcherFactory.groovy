package com.heiko.placelocator.search

import com.heiko.placelocator.exceptions.GoogleAPILocatorException
import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.http.URLBuilder
import com.heiko.placelocator.parser.ResponseParser
import com.heiko.placelocator.search.Impl.TargetSearchProcessor

class SearcherFactory {

    /**
     * Creates the default search algorithm implementation
     *
     * @param config {@code ConfigObject} with configuration parameters
     * @return a {@link TargetSearchProcessor} object
     * @throws GoogleAPILocatorException if a chosen search algorithm isn't implemented
     */
    SearchProcessor create(ResponseParser responseParser, HTTPClient httpClient,
                            URLBuilder urlBuilder, ConfigObject config) {

        SearchProcessor searcherProcessor

        if (config.placeSearcherType == 'Target searcher') {
            searcherProcessor = new TargetSearchProcessor(urlBuilder, httpClient, responseParser, config)
        } else {
            throw new GoogleAPILocatorException('The script does not have such a search algorithm ' +
                    "implementation ($config.placeSearcherType)")
        }

        return searcherProcessor
    }
}