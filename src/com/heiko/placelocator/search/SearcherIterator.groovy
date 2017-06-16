package com.heiko.placelocator.search

import com.heiko.placelocator.location.Places

/**
 * An iterator over a search algorithm
 */
interface SearcherIterator {

    /**
     * Returns {@code true} if the result is not achieved. (In other words,
     * returns {@code true} if {@link #doSearch()} would return a new
     * relevant result)
     *
     * @return {@code true} if the result is not achieved
     */
    boolean isSearchFinished()

    /**
     * Returns the next search results.
     *
     * @return the next search results
     * @throws {@code SearchWasFinishedException} if the result was achieved
     */
    Places doSearch()
}
