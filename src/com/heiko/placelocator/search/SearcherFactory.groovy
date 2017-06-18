package com.heiko.placelocator.search

import com.heiko.placelocator.exceptions.GoogleAPILocatorException
import com.heiko.placelocator.location.Places
import com.heiko.placelocator.search.Impl.TargetSearchProcessor

class SearcherFactory {

    /**
     * Creates the default search algorithm implementation
     *
     * @param config {@code ConfigObject} with configuration parameters
     * @return a {@link TargetSearchProcessor} object
     * @throws GoogleAPILocatorException if a chosen search algorithm isn't implemented
     */
    SearchProcessor create(ConfigObject config) {

        SearchProcessor searcherProcessor

        if (config.placeSearcherType == 'Target searcher') {
            searcherProcessor =
                    new TargetSearchProcessor(config.possibleLocationsNumber as int,
                            config.maxIterationsNumber as int,
                            config.searchRadius as int,
                            config.searchRate as int)
        } else {
            throw new GoogleAPILocatorException('The script does not have such a search algorithm ' +
                    "implementation ($config.placeSearcherType)")
        }

        return searcherProcessor
    }
}