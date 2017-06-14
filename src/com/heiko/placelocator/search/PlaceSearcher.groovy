package com.heiko.placelocator.search

import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.http.URLBuilder
import com.heiko.placelocator.location.Locations
import com.heiko.placelocator.location.Places
import com.heiko.placelocator.parser.ResponseParser


class PlaceSearcher implements Searcher {

    private int iterationsCounter
    private int radius
    private int tendency
    private int rate
    private Places places
    private ResponseParser responseParser
    private HTTPClient httpClient
    private URLBuilder urlBuilder
    private Locations locations
    private List excludedTypes
    private double initialLat
    private double initialLng

    PlaceSearcher(ResponseParser responseParser, HTTPClient httpClient, URLBuilder urlBuilder, ConfigObject config) {
        this.responseParser = responseParser
        this.httpClient = httpClient
        this.urlBuilder = urlBuilder
        this.excludedTypes = config.excludedTypes
        initialLat = config.latitude
        initialLng = config.longitude
        this.locations = new Locations()

        iterationsCounter = 0
        radius = urlBuilder.getOption('radius')
        tendency = 0
        rate = config.rate
    }

    /**
     * Checks search condition:
     * - if it wasn't the last iteration then
     * - if nothing found - increases radius:
     *      if tendency is negative then
     *          radius = radius * rate / 2
     *          radius = radius * rate / 1
     *          tendency = 1
     *
     * - if more than 10 places was found and it wasn't the first iteration then
     *          rate = rate / 2
     *          radius = radius / rate
     *          tendency = -1
     *
     * In other cases, continues search
     *
     * @return true if results is achieved or the iteration number is greater then 4
     */
    private boolean check() {
        boolean result = true

        if (iterationsCounter != 4) {
            if (places.getSize() == 0) {
                radius *= rate / (tendency < 0 ? 2 : 1)
                tendency = 1
                result = false
            } else if (places.getSize() > 10 && iterationsCounter > 1) {
                rate /= 2
                radius /= rate
                tendency = -1
                result = false
            }
        }

        return result
    }

    private void finish() {
        // create Locations object
    }

    private void change() {
        urlBuilder.changeOption("radius", radius)
    }

    @Override
    SearcherIterator getSearch() {
        new Iterator()
    }

    @Override
    Locations getResults() {
        return locations
    }

    private class Iterator implements SearcherIterator {

        @Override
        boolean isSearchFinished() {
            return iterationsCounter || locations.result.size() != 0
        }

        @Override
        void doSearch() {

            iterationsCounter++
            Map results = responseParser.parse(httpClient.get(urlBuilder.get()))
            places = new Places(results.results, excludedTypes, initialLat, initialLng)

            if (check()) {
                finish()
            } else {
                change()
            }
        }
    }
}
