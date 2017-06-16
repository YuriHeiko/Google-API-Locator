package com.heiko.placelocator.response

import com.heiko.placelocator.location.Places

class Response {
    private String status
    private Places places
    private String description

    String getDescription() {
        description
    }

    Places getPlaces() {
        places
    }

    String getStatus() {
        status
    }

    Response(String status, String description) {
        this.status = status
        this.description = description
    }

    Response(Places places, int maxLocationNumber) {

        if (places.getSize() > 0) {
            status = 'OK'
            int firstDistance = places.getPlaces().first().getDistance()
            int lastIndex = 0

            places.getPlaces().each { e ->
                if (Math.abs(firstDistance - e.getDistance() as int) < 14)
                    lastIndex++
            }

            this.places = places.getPart(0, lastIndex > maxLocationNumber ? maxLocationNumber : lastIndex)

        } else {
            status = 'ZERO_RESULTS'
            this.places = places
        }
    }

    @Override
    String toString() {
        String result

        if (description){
            result = /["status": "$status", "description": "$description"]/
        } else {
            result = /["status": "$status", "locations": $places]/
        }

        return result
    }
}
