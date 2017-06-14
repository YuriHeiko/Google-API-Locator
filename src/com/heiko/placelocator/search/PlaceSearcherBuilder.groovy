package com.heiko.placelocator.search

import com.heiko.placelocator.search.simplesearch.SimplePlaceSearcher

/**
 * This is an util class
 */
class PlaceSearcherBuilder {

    /**
     * Creates the default search algorithm implementation
     *
     * @param config {@code ConfigObject} with configuration parameters
     * @return {@code PlaceSearcher} object
     */
    PlaceSearcher get(ConfigObject config) {
        PlaceSearcher placeSearcher

        switch (config.PlaceSearcher) {

            case "Simple":
            default:
                placeSearcher = new SimplePlaceSearcher()
        }

        return placeSearcher
    }
}
