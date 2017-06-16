package com.heiko.placelocator.search2

interface SearchProcessor<T> {
    /**
     * Returns true if the result is not achieved. (In other words, returns
     * true if getNextFactor() would return a new relevant result)
     *
     * @return {@code true} if the result is not achieved
     */
    boolean isSearchFinished(T t)

    /**
     *
     * @return
     */
    int getNextFactor(T t)
}
