package com.heiko.placelocator.search2

import com.heiko.placelocator.location.Places

interface SearcherIterator {

    boolean isSearchFinished()

    Places doSearch()
}
