package com.heiko.placelocator.search.simplesearch

class PlacesComparator implements Comparator {

    @Override
    int compare(Object o1, Object o2) {
        def loc1 = o1.geometry.location
        def loc2 = o2.geometry.location

        return loc1.distance <=> loc2.distance
    }
}
