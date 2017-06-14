package com.heiko.placelocator.google

import static com.heiko.placelocator.google.CalculateDistance.calculate

class Places {
    final List<Place> places

    Places(List<Place> places, List<String> excludedTypes, double initialLat, double initialLong) {

        this.places = new ArrayList<>()

        places.each { e ->
            if (e.types.size() == (e.types - excludedTypes).size()) {

                e.location.put('distance', calculate(initialLat, initialLong, e.location.latitude, e.location.longitude))

                this.places.add(new Place(e.location, e.name, e.place_id, e.types, e.vicinity))
            }
        }
    }
}
