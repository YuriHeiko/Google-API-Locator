package com.heiko.placelocator

import com.heiko.placelocator.exceptions.GoogleAPILocatorException
import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.http.HTTPClientFactory
import com.heiko.placelocator.http.URLBuilder
import com.heiko.placelocator.initializers.CommandLineParser
import com.heiko.placelocator.initializers.ConfigReader
import com.heiko.placelocator.location.Places
import com.heiko.placelocator.parser.ParserFactory
import com.heiko.placelocator.parser.ResponseParser
import com.heiko.placelocator.response.Response
import com.heiko.placelocator.search.PlaceSearcherFactory
import com.heiko.placelocator.search.SearcherIterator

/**
 * Uses Google Places Web API to find a nearest possible location
 * by GPS coordinates.
 *
 * @args command line arguments
 * @return nearest possible places ranged from 1 to 5
 */

try {
    // Read the initial configuration from the default configuration file
    ConfigObject config = ConfigReader.read(new File('Properties.groovy'))

    // Parse command line arguments and put them into the initial configuration object
    config.merge(CommandLineParser.parse(args) as ConfigObject)

    // Get responseParser according to configuration parameters
    final ResponseParser responseParser = new ParserFactory().create(config.inputDataFormat as String)

    // Get HTTPClient
    final HTTPClient httpClient = new HTTPClientFactory().create(config.HTTPClientType as String)

    // Creates URLBuilder object and initializes it according to configuration parameters
    final URLBuilder urlBuilder = new URLBuilder(config.urlOptions as Map, config.urlPrefix as String)

    // Get Searcher
    final SearcherIterator iterator = new PlaceSearcherFactory().create(responseParser, httpClient, urlBuilder, config)

    // Do search
    Places places
    while (!iterator.isSearchFinished()) {
        places = iterator.doSearch()
    }

    new Response(places, config.maxLocationNumber)

} catch (GoogleAPILocatorException e) {
    new Response(e.errorCode, e.getMessage())
}