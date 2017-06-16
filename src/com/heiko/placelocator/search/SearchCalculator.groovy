package com.heiko.placelocator.search

import com.heiko.placelocator.location.Places

class SearchCalculator {
    private int iterationsCounter
    private int tendency
    private int newTendency
    private int radius
    private int rate

    SearchCalculator(int iterationsCounter, int radius, int rate) {
        this.iterationsCounter = iterationsCounter
        this.radius = radius
        this.rate = rate
    }

    int calculateRadius() {
        if (newTendency > 0) {
            radius *= rate / (tendency < 0 ? 2 : 1)
        } else {
            rate /= 2
            radius /= rate
        }
        tendency = newTendency

        radius
    }

    boolean check(Places places) {
        boolean isSearchFinished = true

        if (--iterationsCounter > 0) {
            if (places.getSize() == 0) {
                newTendency = 1
                isSearchFinished = false
            } else if (places.getSize() > 10 && iterationsCounter < 3) {
                newTendency = -1
                isSearchFinished = false
            }
        }

        isSearchFinished
    }
}