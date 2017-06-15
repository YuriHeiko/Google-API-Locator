package com.heiko.placelocator.location

class Place {
    private Map<String, Double> location
    private String name
    private String place_id
    private List<String> types
    private String vicinity
    private double distance

    Place(Map<String, Double> location, String name, String place_id, List<String> types, String vicinity, double distance) {
        this.location = location
        this.name = name
        this.place_id = place_id
        this.types = types
        this.vicinity = vicinity
        this.distance = distance
    }

    double getDistance() {
        return distance
    }

    @Override
    public String toString() {
        // it doesn't work with return ??? WTF
//        return /["location": "$location", "name": "$name", "place_id": "$place_id", "types": "$types", "vicinity": "$vicinity", "distance": "$distance"]/
        /"name": "$name", "description": ["location": "$location", "place_id": "$place_id",/ +
                /"types": "$types", "vicinity": "$vicinity", "distance": "$distance"]/
    }
}
