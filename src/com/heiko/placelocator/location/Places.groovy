package com.heiko.placelocator.location

/**
 * Contains a {@link Place} objects
 */
class Places {
    private final List<Place> places

    /**
     * Constructs this object and fills the List of new Place objects according to incoming
     * arguments. Sort the list according to distance between Place coordinates and the
     * initial point coordinates
     *
     * @param places List of maps contains parsed JSON request from Google Places Web API
     * @param excludedTypes list of types that should not be used to create new Place objects
     * @param initialLat the initial latitude
     * @param initialLng the initial longitude
     */
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

    // TODO !!!!!!!
    /**
     *
     *
     * @param places
     */
    Places(List<Place> places) {
        this.places = places
    }

    /**
     * Returns the size of the Place's list
     *
     * @return the size of the Place's list
     */
    int getSize() {
        places.size()
    }

    /**
     * Returns the Place's list
     *
     * @return the Place's list
     */
    List<Place> getPlaces() {
        places
    }

    Places getPart(int startIndex, int lastIndex) {
        new Places(new ArrayList<Place>(places.subList(startIndex, lastIndex)))
    }

    /**
     * Froms and returns the string representation of this object
     *
     * @return
     */
    @Override
    String toString() {
        places
    }
}