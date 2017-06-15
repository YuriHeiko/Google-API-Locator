package com.heiko.placelocator

import com.heiko.placelocator.exceptions.GoogleAPILocatorException
import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.http.HTTPClientBuilder
import com.heiko.placelocator.http.URLBuilder
import com.heiko.placelocator.initializers.Initializer
import com.heiko.placelocator.parser.ParserBuilder
import com.heiko.placelocator.parser.ResponseParser
import com.heiko.placelocator.search.PlaceSearcherBuilder
import com.heiko.placelocator.search.Searcher
import com.heiko.placelocator.search.SearcherIterator

/**
 * Uses Google Places Web API to find a nearest possible location
 * by GPS coordinates.
 *
 * @args command line arguments
 * @return nearest possible places ranged from 1 to 5
 */

try {
    // Read the initial configuration from the default configuration file, parse
    // command line arguments and put them into the initial configuration object
    final ConfigObject config = Initializer.getConfiguration(args)

    // Get parser according to configuration parameters
    final ResponseParser responseParser = new ParserBuilder().get(config.format as String)

    // Get HTTPClient
    final HTTPClient httpClient = new HTTPClientBuilder().get(config.HTTPClient as String)

    // Creates URLBuilder object and initializes it according to configuration parameters
    final URLBuilder urlBuilder = new URLBuilder(config.urlOptions as Map, config.urlPrefix as String)

    // Get Searcher
    final Searcher searcher = new PlaceSearcherBuilder().get(responseParser, httpClient, urlBuilder, config)

    SearcherIterator search = searcher.getSearch()

    while (search.isSearchNeeded()) {
        search.doSearch()
    }

    println searcher.getResults()

    return searcher.getResults()

} catch (GoogleAPILocatorException e) {


    println e.getMessage()

    return e.getMessage()

}