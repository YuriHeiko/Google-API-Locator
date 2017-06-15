package com.heiko.placelocator.search

import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.http.URLBuilder
import com.heiko.placelocator.location.Places
import com.heiko.placelocator.parser.ResponseParser

class TargetPlaceSearcher implements Searcher {

    private final String ZERO_RESULTS = /["status": "ZERO_RESULTS"]/
    private final String STATUS_OK = /["status": "OK", "location": /
    private final int MAX_ITERATIONS_NUMBER = 4

    private int iterationsCounter
    private int radius
    private int tendency
    private int rate
    private Places placesPrev
    private Places places
    private ResponseParser responseParser
    private HTTPClient httpClient
    private URLBuilder urlBuilder
    private List excludedTypes
    private double initialLat
    private double initialLng
    private boolean isLocationFound

    TargetPlaceSearcher(ResponseParser responseParser, HTTPClient httpClient, URLBuilder urlBuilder, ConfigObject config) {
        this.responseParser = responseParser
        this.httpClient = httpClient
        this.urlBuilder = urlBuilder

        excludedTypes = config.excludedTypes
        initialLat = config.latitude
        initialLng = config.longitude
        radius = urlBuilder.getOption('radius') as int
        rate = config.rate
        iterationsCounter = MAX_ITERATIONS_NUMBER
    }

    private StringBuilder findLocation() {
        if (iterationsCounter > 0) {
            if (places.getSize() == 0) {
                changeRadius(1)
            } else if (places.getSize() > 10 && iterationsCounter < 4) {
                placesPrev = places
                changeRadius(-1)
            }
        }

        return makeResponse()
    }

    private void changeRadius(int newTendency) {
        if (tendency > 0) {
            radius *= rate / (tendency < 0 && newTendency > 0 ? 2 : 1)
        } else {
            rate /= 2
            radius /= rate
        }
        tendency = newTendency

        urlBuilder.setOption("radius", radius)
    }

    private StringBuilder makeResponse() {
        StringBuilder response = new StringBuilder()

        if (places.getSize() == 0 && placesPrev)
            places = placesPrev

        if (places.getSize() > 0) {
            response << STATUS_OK
            int firstDistance = places.getPlaces().first().getDistance()
            int lastIndex = 0

            places.getPlaces().each { e ->
                if (Math.abs(firstDistance - e.getDistance() as int) < 14)
                    lastIndex++
            }

            response << places.getResponse(lastIndex)

        } else {
            response << ZERO_RESULTS
        }

        return response
    }

    @Override
    SearcherIterator getSearch() {
        new Iterator()
    }

    private class Iterator implements SearcherIterator {

        @Override
        boolean isSearchFinished() {
            !(iterationsCounter || isLocationFound)
        }

        @Override
        String doSearch() {

            iterationsCounter--

            Map results = responseParser.parse(httpClient.get(urlBuilder.get()) as String)
            places = new Places(results.results, excludedTypes, initialLat, initialLng)

            return findLocation()
        }
    }
}
