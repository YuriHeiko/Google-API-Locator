package com.heiko.placelocator.search.simplesearch

import com.heiko.placelocator.location.CalculateDistance
import com.heiko.placelocator.search.PlaceSearcher

class SimplePlaceSearcher implements PlaceSearcher {

    String resultString

    @Override
    def find(final List results, final ConfigObject config) {

        // TODO: Envisage situation when there are some points which have almost equal distance from the starting point!

        if (results.size() == 0)
            return resultString

        results.each { element ->
            def location = element.geometry.location
            location.put("distance",
                    CalculateDistance.calculate(location.lat, location.lng,
                            config.latitude, config.longitude))
        }

        results.sort(new PlacesComparator())

        boolean isDistanceOK = checkDistance(results, config.radius)
        if (config.radius <= 10) {

            if (results.size() > 5 && isDistanceOK) {
                buildResultingString(results.subList(0, 5))

            } else if (isDistanceOK) {
                println isDistanceOK
                println results
                buildResultingString(results)
            }

        } else if (isDistanceOK) {
            buildResultingString(results.subList(0, 1))
        }

        results.each {
            it.each { k, v ->
                println "$k : $v"
            }
            println()
        }

        println new String(new byte[90]).replaceAll(".", "-")
        println()

        return resultString
    }

    @Override
    boolean isResultAchieved() {
        return this.resultString != null
    }

    @Override
    boolean changeSearch(final ConfigObject config) {

        if (config.radius <= 10) {
            config.radius = 50
        } else if (config.radius <= 50) {
            config.radius = 100
        } else if (config.radius <= 100) {
            config.radius = 1000
        } else if (config.radius <= 1000) {
            config.radius = 50000
        } else {
            resultString = '["STATUS":"ZERO_RESULTS", "results" : "The Earth"]'
            return true
        }

        return false
    }

    void buildResultingString(List results) {
        resultString = /["STATUS":"OK", "results" : "$results"]/
    }

    boolean checkDistance(List list, int distance) {
        Double.compare(list[0].geometry.location.distance, distance) < 1
    }
}
