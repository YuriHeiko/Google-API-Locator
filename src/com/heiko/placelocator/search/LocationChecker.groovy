package com.heiko.placelocator.search

import com.heiko.placelocator.location.Places

class LocationChecker {
    private int iterationsCounter
    private int tendency
    private int radius
    private int rate

    LocationChecker(int iterationsCounter, int radius, int rate) {
        this.iterationsCounter = iterationsCounter
        this.radius = radius
        this.rate = rate
    }

    int getRadius() {
        radius
    }

    void calculateRadius(int newTendency) {
        if (tendency > 0) {
            radius *= rate / (tendency < 0 && newTendency > 0 ? 2 : 1)
        } else {
            rate /= 2
            radius /= rate
        }
        tendency = newTendency
    }

    boolean check(Places places) {
        boolean isSearchFinished = true

        if (--iterationsCounter > 0) {

            if (places.getSize() == 0) {
                calculateRadius(1)
                isSearchFinished = false
            } else if (places.getSize() > 10 && iterationsCounter < 3) {
                calculateRadius(-1)
                isSearchFinished = false
            }
        }

        isSearchFinished
    }
}