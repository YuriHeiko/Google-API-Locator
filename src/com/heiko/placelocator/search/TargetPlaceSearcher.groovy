package com.heiko.placelocator.search

import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.http.URLBuilder
import com.heiko.placelocator.location.Places
import com.heiko.placelocator.location.PlacesHolder
import com.heiko.placelocator.parser.ResponseParser

class TargetPlaceSearcher implements SearcherIterator {
    private URLBuilder urlBuilder
    private HTTPClient httpClient
    private ResponseParser responseParser
    private boolean isSearchFinished
    private SearchCalculator searchCalc
    private double initialLat
    private double initialLng
    private List excludedTypes
    private PlacesHolder placesHolder

    TargetPlaceSearcher(URLBuilder urlBuilder, HTTPClient httpClient, ResponseParser responseParser, ConfigObject config) {
        this.urlBuilder = urlBuilder
        this.httpClient = httpClient
        this.responseParser = responseParser

        searchCalc = new SearchCalculator(config.maxIterationNumber, config.searchRadius, config.searchRate)
        initialLat = config.initialLatitude
        initialLng = config.initialLongitude
        excludedTypes = config.excludedTypes
        placesHolder = new PlacesHolder()
    }

    @Override
    boolean isSearchFinished() {
        isSearchFinished
    }

    @Override
    Places doSearch() {
        List results = (responseParser.parse(httpClient.get(urlBuilder.get()) as String)).results
        Places places = new Places(results, excludedTypes, initialLat, initialLng)
        placesHolder.put(places)
        isSearchFinished = searchCalc.check(places)
        urlBuilder.setOption("radius", searchCalc.calculateRadius()) // 1-а будет выполнена в пустую

        placesHolder.get()
    }
}
