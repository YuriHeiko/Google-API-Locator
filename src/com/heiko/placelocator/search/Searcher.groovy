package com.heiko.placelocator.search

import com.heiko.placelocator.location.Locations

interface Searcher {
    SearcherIterator getSearch()

    String getResults()
}