package com.heiko.placelocator.search2.Impl

import com.heiko.placelocator.location.Places
import com.heiko.placelocator.search2.SearchHistory
import com.heiko.placelocator.search2.SearchProcessor

class TargetSearchProcessor implements SearchProcessor<SearchHistory<Places, Integer>> {
    private int iterationsCounter
    private final int MAX_ITERATIONS
    private final int POSSIBLE_LOCATIONS_NUMBER

    @Override
    boolean isSearchFinished(SearchHistory searchHistory) {
        int size = searchHistory.getLast().size()

        return !(--iterationsCounter > 0 && size == 0 || size > POSSIBLE_LOCATIONS_NUMBER && iterationsCounter < MAX_ITERATIONS - 1)
    }

    @Override
    int getNextFactor(SearchHistory searchHistory) {
        // calculate and hold tendencies in SearchHistory class???

        int newTendency = searchHistory.getLast().size() - searchHistory.getBeforeLast().size()
        int oldTendency = searchHistory.getBeforeLast().size() - searchHistory.get(searchHistory.size - 3)


        if (newTendency > 0) {
            radius *= rate / (oldTendency < 0 ? 2 : 1)
        } else {
            rate /= 2
            radius /= rate
        }
        oldTendency = newTendency

        radius
    }
}
