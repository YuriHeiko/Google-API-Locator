package com.heiko.placelocator.utils

import com.heiko.placelocator.search.PlaceSearcher
import com.heiko.placelocator.search.simplesearch.SimplePlaceSearcher
/**
 * This is an util class
 */
class PlaceSearcherBuilder {

    /**
     * This function creates the default search algorithm implementation
     *
     * @param config {@code ConfigObject} with configuration parameters
     * @return {@code PlaceSearcher} object
     */
    static get(ConfigObject config) {
        PlaceSearcher placeSearcher

        switch (config.PlaceSearcher) {
            case "Simple":
            default:
                placeSearcher = new SimplePlaceSearcher()
        }

        return placeSearcher
    }
}
