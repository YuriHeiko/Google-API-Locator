package com.heiko.placelocator.location

/**
 * Representation of the location characteristics returned by Google Places Web API
 */

class Place {
    private Map<String, Double> location
    private String name
    private String place_id
    private List<String> types
    private String vicinity
    private double distance

    /**
     * Constructs this object that represents location
     *
     * @param location map contains location's coordinates
     * @param name the name of the location
     * @param place_id the id of the location
     * @param types the types of the location
     * @param vicinity the vicinity of the location
     */
    Place(Map<String, Double> location, String name, String place_id, List<String> types, String vicinity) {
        this.location = location
        this.name = name
        this.place_id = place_id
        this.types = types
        this.vicinity = vicinity
    }

    /**
     * Returns distance between the initial point coordinates and this ones
     *
     * @return distance between the initial point coordinates and this ones
     */
    double getDistance() {
        return distance
    }

    /**
     * Calculate distance between this point and the initial one in latitude
     * and longitude. Uses Haversine method as its base.
     *
     * @param lat the initial point latitude
     * @param lng the initial point longitude
     */
    void setDistance(double lat, double lng) {

        final int R = 6371 // Radius of the earth

        double latDistance = Math.toRadians(location.lat - lat)
        double lonDistance = Math.toRadians(location.lng - lng)
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(location.lat)) *
                Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2)
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        double distance = R * c * 1000 // convert to meters

        distance = Math.pow(distance, 2)

        this.distance = Math.sqrt(distance)
    }

    /**
     * Froms and returns the string representation of this object which is
     * also a valid JSON string
     *
     * @return the string representation of this object which is also a valid JSON string
     */
    @Override
    String toString() {

        def locationStr = location.toString().
                replaceAll(/\{/, /{"/).
                replaceAll(/}/, /"}/).
                replaceAll(/, /, /, "/).
                replaceAll(/=/, /": /)

        def typesStr = types.toString().
                replaceAll(/\[/, /["/).
                replaceAll(/]/, /"]/).
                replaceAll(/, /, /", "/)

/*
        /"name": "$name", "description": {"location": $locationStr, "place_id": "$place_id",/ +
                /"types": $typesStr, "vicinity": "$vicinity", "distance": $distance}/
*/
        /"name": "$name", "description": $locationStr, "distance": $distance, / +
                /"types": $typesStr, "vicinity": "$vicinity"}/
    }
}