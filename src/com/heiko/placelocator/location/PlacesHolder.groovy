package com.heiko.placelocator.location

/**
 * Holds two {@code Places} objects and returns a more appropriate one
 */
class PlacesHolder {
    private Places placesNext
    private Places placesPrev

    /**
     * Puts a new object
     *
     * @param places
     */
    void put(Places places) {
        placesPrev = placesNext
        placesNext = places
    }

    /**
     * Returns a more appropriate object according to incapsulated data
     * @return a {@code Places} object
     */
    Places get() {
        placesNext.getSize() == 0 && placesPrev ? placesPrev : placesNext
    }
}