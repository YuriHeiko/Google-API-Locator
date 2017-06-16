package com.heiko.placelocator.location

class PlacesHolder {
    private Places placesNext
    private Places placesPrev

    void put(Places places) {
        placesPrev = placesNext
        placesNext = places
    }

    Places get() {
        placesNext.getSize() == 0 && placesPrev ? placesPrev : placesNext
    }
}