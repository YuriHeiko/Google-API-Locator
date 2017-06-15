package com.heiko.placelocator.location

import static com.heiko.placelocator.location.CalculateDistance.calculate

class Places {
    private final List<Place> places

    Places(List places, List<String> excludedTypes, double initialLat, double initialLng) {

        this.places = new ArrayList<>()

        places.each { e ->
            if (e.types.size() == (e.types - excludedTypes).size()) {

                def location = e.geometry.location

                location.put('distance', calculate(initialLat, initialLng, location.lat, location.lng))

                this.places.add(new Place(location, e.name, e.place_id, e.types, e.vicinity))
            }
        }

        places.sort({p1,p2 ->
            p1.location.distance <=> p2.location.distance
        })
    }

    int getSize() {
        return places.size()
    }

    List<Places> getPlaces() {
        return places
    }

}
