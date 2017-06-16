package com.heiko.placelocator.location

class Place {

    private Map<String, Double> location
    private String name
    private String place_id
    private List<String> types
    private String vicinity
    private double distance

    Place(Map<String, Double> location, String name, String place_id, List<String> types, String vicinity) {
        this.location = location
        this.name = name
        this.place_id = place_id
        this.types = types
        this.vicinity = vicinity
    }

    double getDistance() {
        return distance
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat, lng Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
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
