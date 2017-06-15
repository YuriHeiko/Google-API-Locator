package com.heiko.placelocator.search

interface Searcher {
    SearcherIterator getSearch()

    String getResults()
}