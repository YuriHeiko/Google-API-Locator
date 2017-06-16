package com.heiko.placelocator.response

import com.heiko.placelocator.location.Places

/**
 * Contains the closest places to some initial location
 */
class Response {
    private String status
    private Places places
    private String description
    private final int GPS_ERROR

    /**
     * Constructs the object in case of some error occurred
     *
     * @param status the status of the search algorithm work
     * @param description the error status description
     */
    Response(String status, String description) {
        this.status = status
        this.description = description
        GPS_ERROR = 0
    }

    /**
     * Constructs the object contains from 1 to 5 the nearest locations to
     * the initial location. Firstly, it counts elements from the first which
     * are not further then GPS_ERROR meters from each other, Finally, it
     * returns these elements but no more then maxLocationNumber
     *
     * @param places a {@code Places} object contains the nearest locations to
     * the initial location
     * @param maxLocationNumber the maximum number of the closest locations
     */
    Response(Places places, int maxLocationNumber, int gpsError) {
        this.GPS_ERROR = gpsError

        if (places.getSize() > 0) {
            status = 'OK'
            int firstDistance = places.getPlaces().first().getDistance()
            int lastIndex = 0

            places.getPlaces().each { e ->
                if (Math.abs(firstDistance - e.getDistance() as int) < GPS_ERROR)
                    lastIndex++
            }

            this.places = places.getPart(0, lastIndex > maxLocationNumber ? maxLocationNumber : lastIndex)

        } else {
            status = 'ZERO_RESULTS'
            this.places = places
        }
    }

    /**
     * Returns the error status description
     *
     * @return the error status description
     */
    String getDescription() {
        description
    }

    /**
     * Returns
     * @return
     */
    Places getPlaces() {
        places
    }

    /**
     * Returns the status of the search algorithm work
     *
     * @return the status of the search algorithm work
     */
    String getStatus() {
        status
    }

    /**
     * Froms and returns the string representation of this object which is
     * also a valid JSON string
     *
     * @return the string representation of this object which is also a valid JSON string
     */
    @Override
    String toString() {
        String result

        if (description) {
            result = /["status": "$status", "description": "$description"]/
        } else {
            result = /["status": "$status", "locations": $places]/
        }

        return result
    }
}
