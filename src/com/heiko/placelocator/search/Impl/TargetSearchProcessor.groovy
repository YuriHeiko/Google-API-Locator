package com.heiko.placelocator.search.Impl

import com.heiko.placelocator.location.Places
import com.heiko.placelocator.search.HistoryHolder
import com.heiko.placelocator.search.SearchProcessor

class TargetSearchProcessor implements SearchProcessor<HistoryHolder<Integer, Places, Integer>> {
    private final int POSSIBLE_LOCATIONS_NUMBER
    private final int MAX_ITERATIONS
    private int iterationsCounter
    private final int INITIAL_RADIUS
    private int rate

    TargetSearchProcessor(int POSSIBLE_LOCATIONS_NUMBER, int MAX_ITERATIONS, int INITIAL_RADIUS, int rate) {
        this.POSSIBLE_LOCATIONS_NUMBER = POSSIBLE_LOCATIONS_NUMBER
        this.MAX_ITERATIONS = MAX_ITERATIONS
        this.INITIAL_RADIUS = INITIAL_RADIUS
        this.rate = rate
        iterationsCounter = MAX_ITERATIONS
    }

    @Override
    boolean isSearchFinished(HistoryHolder<Integer, Places, Integer> searchHistory) {
        int size = searchHistory.getFirstValue(0) ? searchHistory.getFirstValue(0).getSize() : 0

        return !(--iterationsCounter > 0 && size == 0 ||
                size > POSSIBLE_LOCATIONS_NUMBER && iterationsCounter < MAX_ITERATIONS - 1)
    }

    @Override
    int getNextFactor(HistoryHolder<Integer, Places, Integer> searchHistory) {
        int newTendency = 0
        int oldTendency = 0
        int radius = INITIAL_RADIUS

        if (searchHistory.getFirstValue(0)) {
            radius = searchHistory.getKey(0)
            newTendency = searchHistory.getSize() > 1 ? radius - searchHistory.getKey(1) : 0
            searchHistory.setSecondValueByKey(radius, newTendency)

            oldTendency = searchHistory.getFirstValue(1) == null ? 0 : searchHistory.getSecondValue(1)
        }

        if (newTendency > 0) {
            radius *= rate / (oldTendency < 0 ? 2 : 1)
        } else if (newTendency < 0) {
            rate /= 2
            radius /= rate
        }

        radius
    }
}