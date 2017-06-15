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
    private Places placesPrev
    private Places places
    private ResponseParser responseParser
    private HTTPClient httpClient
    private URLBuilder urlBuilder
    private List excludedTypes
    private double initialLat
    private double initialLng
    private String result

    PlaceSearcher(ResponseParser responseParser, HTTPClient httpClient, URLBuilder urlBuilder, ConfigObject config) {
        this.responseParser = responseParser
        this.httpClient = httpClient
        this.urlBuilder = urlBuilder
        this.excludedTypes = config.excludedTypes
        initialLat = config.latitude
        initialLng = config.longitude

        iterationsCounter = 0
        radius = urlBuilder.getOption('radius') as int
        tendency = 0
        rate = config.rate
    }

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

                placesPrev = places
            }
        }

        return result
    }

    private void finish() {
        if (places.getSize() == 0)
            places = placesPrev

        places.getPlaces().first().

        places.getPlaces().each { e ->

        }

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
        return result
    }

    private class Iterator implements SearcherIterator {

        @Override
        boolean isSearchFinished() {
            return iterationsCounter || locations.result.size() != 0
        }

        @Override
        void doSearch() {

            iterationsCounter++
            Map results = responseParser.parse(httpClient.get(urlBuilder.get()) as String)
            places = new Places(results.results, excludedTypes, initialLat, initialLng)

            if (check()) {
                finish()
            } else {
                change()
            }
        }
    }
}
