package com.heiko.placelocator.search

import com.heiko.placelocator.exceptions.GoogleAPILocatorException
import com.heiko.placelocator.search.simplesearch.SimplePlaceSearcher

/**
 * This is an util class
 */
class PlaceSearcherBuilder {

    /**
     * Creates the default search algorithm implementation
     *
     * @param config {@code ConfigObject} with configuration parameters
     * @return {@link PlaceSearcher} object
     * @throws GoogleAPILocatorException if a chosen search algorithm isn't implemented
     */
    PlaceSearcher get(ConfigObject config) {
        final PlaceSearcher placeSearcher

        if (config.PlaceSearcher == 'simple') {
            placeSearcher = new SimplePlaceSearcher()
        } else {
            throw new GoogleAPILocatorException("The script doesn't have such a search algorithm implementation ($config.PlaceSearcher)")
        }

        return placeSearcher
    }
}
