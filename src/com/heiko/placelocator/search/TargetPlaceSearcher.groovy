package com.heiko.placelocator.search

import com.heiko.placelocator.exceptions.SearchWasFinishedException
import com.heiko.placelocator.google.GoogleAPI
import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.http.URLBuilder
import com.heiko.placelocator.location.Places
import com.heiko.placelocator.location.PlacesHolder
import com.heiko.placelocator.parser.ResponseParser

/**
 * Searches the closest location to the initial coordinates. Uses several attempts
 * (no more then {@code config.maxIterationNumber}) and adapt the searching radius
 * according achieved results. Implements {@link SearcherIterator}
 */
class TargetPlaceSearcher implements SearcherIterator {
    private GoogleAPI googleAPI
    private URLBuilder urlBuilder

    private SearchCalculator searchCalc
    private PlacesHolder placesHolder
    private List excludedTypes
    private double initialLat
    private double initialLng
    private boolean isSearchFinished

    /**
     * Constructs an object according to received arguments. Sets the initial parameters.
     *
     * @param urlBuilder an {@link URLBuilder} object
     * @param httpClient an {@link HTTPClient} object
     * @param responseParser an {@link ResponseParser} object
     * @param config an {@link ConfigObject} object contains all initial properties
     */
    TargetPlaceSearcher(GoogleAPI googleAPI, URLBuilder urlBuilder, ConfigObject config) {
        this.googleAPI = googleAPI
        this.urlBuilder = urlBuilder

        searchCalc = new SearchCalculator(config.maxIterationsNumber, config.possibleLocationsNumber,
                config.searchRadius, config.searchRate)
        initialLat = config.initialLatitude
        initialLng = config.initialLongitude
        excludedTypes = config.excludedTypes
        placesHolder = new PlacesHolder()
    }

    /**
     * Returns true if the result is achieved (isSearchFinished = true).
     * (In other words, returns true if {@link #doSearch()} would
     * return a new relevant result.)
     *
     * @return true if the result is not achieved
     */
    @Override
    boolean isSearchFinished() {
        isSearchFinished
    }

    /**
     * Uses {@code URLBuilder}, {@code HTTPClient}, {@code ResponseParser}
     * objects to make a HTTP request to Google Place Web API and parse a
     * response. Uses {@code SearchCalculator} to check the obtained result,
     * calculate and set the new radius, set the isSearchFinished flag.
     * Returns the result.
     *
     * @return a {@link Places} object with found closest locations
     * @throws SearchWasFinishedException if the result was achieved
     */
    @Override
    Places doSearch() {
        if (isSearchFinished) {
            throw new SearchWasFinishedException("Attempt to iterate over a finished search")
        }

        List results = googleAPI.doGet(urlBuilder.get())
        Places places = new Places(results, excludedTypes, initialLat, initialLng)
        placesHolder.put(places)
        isSearchFinished = searchCalc.check(places)
        urlBuilder.setOption("radius", searchCalc.calculateRadius())

        placesHolder.get()
    }
}