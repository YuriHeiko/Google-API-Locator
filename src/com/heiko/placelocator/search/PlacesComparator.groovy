package com.heiko.placelocator.search

import com.heiko.placelocator.location.Place

class PlacesComparator implements Comparator<Place> {

    @Override
    int compare(Place p1, Place p2) {
        return p1.location.distance <=> p2.location.distance
    }
}
