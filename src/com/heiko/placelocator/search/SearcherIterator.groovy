package com.heiko.placelocator.search

interface SearcherIterator {

    boolean isSearchNeeded()

    void doSearch()
}
