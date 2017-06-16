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

    /**
     * Constructs a new object
     *
     * @param places the already formed list of Place's locations
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

    /**
     * Returns a new Places object that contains a part of initial held
     * Place objects.
     *
     * @param startIndex the start index
     * @param lastIndex the last index
     * @return a new Places object that contains a part of initial held
     * Place objects
     */
    Places getPart(int startIndex, int lastIndex) {
        new Places(new ArrayList<Place>(places.subList(startIndex, lastIndex)))
    }

    /**
     * Froms and returns the string representation of this object
     *
     * @return the string representation of this object
     */
    @Override
    String toString() {
        places
    }
}