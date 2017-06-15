package com.heiko.placelocator.location

class Places {

    private final List<Place> places

    Places(List places, List<String> excludedTypes, double initialLat, double initialLng) {

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

    int getSize() {
        places.size()
    }

    List<Place> getPlaces() {
        places
    }

    String getResponse(int lastIndex) {

        StringBuilder response = new StringBuilder('{')

        places.subList(0, lastIndex).each { e ->
            response << e.toString() << ','
        }

        response.deleteCharAt(response.size() - 1) << '}'

        return response
    }

}
