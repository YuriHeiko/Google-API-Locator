package com.heiko.placelocator.search

import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.http.URLBuilder
import com.heiko.placelocator.location.Places
import com.heiko.placelocator.parser.ResponseParser

class TargetPlaceSearcher implements SearcherIterator {
    private URLBuilder urlBuilder
    private HTTPClient httpClient
    private ResponseParser responseParser
    private Places places
    private Places placesPrev
    private boolean isLocationFound
    private LocationChecker locationChecker
    private double initialLat
    private double initialLng
    List excludedTypes

    TargetPlaceSearcher(URLBuilder urlBuilder, HTTPClient httpClient, ResponseParser responseParser, ConfigObject config) {
        this.urlBuilder = urlBuilder
        this.httpClient = httpClient
        this.responseParser = responseParser

        locationChecker = new LocationChecker(config.iterationNumber, config.radius, config.rate)
        initialLat = config.latitude
        initialLng = config.longitude
        excludedTypes = config.excludedTypes
    }

    @Override
    boolean isSearchFinished() {
        isLocationFound
    }

    @Override
    Places doSearch() {

        def apiResponse = httpClient.get(urlBuilder.get()) as String

        List results = (responseParser.parse(apiResponse)).results

        places = new Places(results, excludedTypes, initialLat, initialLng)

        int tendency = locationChecker.check(places)

        if (tendency != 0) {
            radiusChanger.change(tendency)
        } else {
            isLocationFound = true

            if (places.getSize() == 0 && locationChecker.getPlaces()) {
                places = placesPrev
            }
        }

        places
    }
}
