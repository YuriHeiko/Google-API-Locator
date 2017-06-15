package com.heiko.placelocator.location

class Places {

    private final List<Place> places

    Places(List<Place> places, List<String> excludedTypes, double initialLat, double initialLng) {

        this.places = new ArrayList<>()

        places.each { e ->
            if (e.types.size() == (e.types - excludedTypes).size()) {

                def location = e.geometry.location

                def place = new Place(location, e.name, e.place_id, e.types, e.vicinity)
                place.setDistance(initialLat, initialLng)
                this.places.add(place)
            }
        }

        this.places.sort({ p1, p2 ->
            p1.getDistance() <=> p2.getDistance()
        })
    }

    Places(List<Place> places) {
        this.places = places
    }

    int getSize() {
        places.size()
    }

    List<Place> getPlaces() {
        places
    }

    Places getPart(int startIndex, int lastIndex) {
        new Places(new ArrayList<Place>(places.subList(startIndex, lastIndex)))
    }


    @Override
    public String toString() {
        places
    }
}
