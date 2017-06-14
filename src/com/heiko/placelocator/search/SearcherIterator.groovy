package com.heiko.placelocator.search

import com.heiko.placelocator.location.Locations

interface SearcherIterator {
    boolean isSearchFinished()

    void doSearch()
}
