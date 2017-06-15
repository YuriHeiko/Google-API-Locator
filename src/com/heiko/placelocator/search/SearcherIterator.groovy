package com.heiko.placelocator.search

import com.heiko.placelocator.location.Places

interface SearcherIterator {

    boolean isSearchFinished()

    Places doSearch()
}
