package com.heiko.placelocator.search

import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.http.URLBuilder
import com.heiko.placelocator.location.Locations
import com.heiko.placelocator.location.Places
import com.heiko.placelocator.parser.ResponseParser

class PlaceSearcher implements Searcher {

    private int iterations = 3
    private int searchSpeed = 2

    ResponseParser responseParser
    HTTPClient httpClient
    URLBuilder urlBuilder
    Locations locations
    List excludedTypes
    double initialLat
    double initialLng

    PlaceSearcher(ResponseParser responseParser, HTTPClient httpClient, URLBuilder urlBuilder, ConfigObject config) {
        this.responseParser = responseParser
        this.httpClient = httpClient
        this.urlBuilder = urlBuilder
        this.excludedTypes = config.excludedTypes as List
        initialLat = config.latitude
        initialLng = config.longitude
        this.locations = new Locations()
    }

    private boolean check(boolean hasNextPage) {
        if (hasNextPage) {

        }

        urlBuilder.changeOption("radius", "50")

        return true
    }

    private void finish() {
        // create Locations object
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
            return iterations || locations.result.size() != 0
        }

        @Override
        void doSearch() {

            iterations--

            Map results = responseParser.parse(httpClient.get(urlBuilder.get()))

            Places places = new Places(results.results, excludedTypes, initialLat, initialLng)

            if (check(results.next_page_token)) {
                finish()
            }
        }
    }
}
