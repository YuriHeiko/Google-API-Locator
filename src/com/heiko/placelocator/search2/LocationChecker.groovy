package com.heiko.placelocator.search2

import com.heiko.placelocator.location.Places

class LocationChecker {

    private Places places
    private int iterationsCounter

    LocationChecker(int iterationsCounter) {
        this.iterationsCounter = iterationsCounter
    }

    Places getPlaces() {
        places
    }

    int check(Places places) {

        int tendency = 0

        if (iterationsCounter > 0) {
            if (places.getSize() == 0) {
                tendency = 1
            } else if (places.getSize() > 10 && iterationsCounter < 4) {
                this.places = places
                tendency = -1
            }
        }

        tendency
    }
}
