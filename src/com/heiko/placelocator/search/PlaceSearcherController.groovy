package com.heiko.placelocator.search

/**
 * Delegator Class to delegate the implementation of the search algorithm
 *
 */
class PlaceSearcherController {

    final PlaceSearcher placeSearcher

    PlaceSearcherController(PlaceSearcher placeSearcher) {
        this.placeSearcher = placeSearcher
    }

    def find(final List results, final ConfigObject config) {
        placeSearcher.find(results, config)
    }

    boolean isResultAchieved() {
        placeSearcher.isResultAchieved()
    }

    boolean changeSearch(final ConfigObject config) {
        placeSearcher.changeSearch(config)
    }
}
