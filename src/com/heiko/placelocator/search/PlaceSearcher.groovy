package com.heiko.placelocator.search

interface PlaceSearcher {

    def find(final List results, final ConfigObject config)

    boolean isResultAchieved()

    boolean changeSearch(final ConfigObject config)
}
