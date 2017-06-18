package com.heiko.placelocator.search

interface SearchProcessor<T> {
    /**
     * Returns true if the result is not achieved. (In other words, returns
     * true if getNextFactor() would return a new relevant result)
     *
     * @return  true  if the result is not achieved
     */
    boolean isSearchFinished(T t)

    /**
     *
     * @return
     */
    int getNextFactor(T t)
}