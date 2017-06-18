package com.heiko.placelocator

import com.heiko.placelocator.exceptions.GoogleAPILocatorException
import com.heiko.placelocator.google.GoogleAPI
import com.heiko.placelocator.google.GooglePlacesWebAPI
import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.http.HTTPClientFactory
import com.heiko.placelocator.http.URLBuilder
import com.heiko.placelocator.initializers.CommandLineParser
import com.heiko.placelocator.initializers.ConfigReader
import com.heiko.placelocator.location.Places
import com.heiko.placelocator.parser.ParserFactory
import com.heiko.placelocator.parser.ResponseParser
import com.heiko.placelocator.response.Response
import com.heiko.placelocator.search.HistoryHolder
import com.heiko.placelocator.search.Impl.SearchHistoryLIFO
import com.heiko.placelocator.search.SearchProcessor
import com.heiko.placelocator.search.SearcherFactory

/**
 * Uses Google Places Web API to find a nearest possible location
 * by GPS coordinates.
 *
 * @args command line arguments
 * @return a {@link Response} object contains nearest possible places
 * ranged from 1 to config.maxLocationNumber, if they are found. If
 * search results are not achieved, {@code Response} object contains
 * the error status and description
 */

try {
    final ConfigObject config = ConfigReader.read(new File('Properties.groovy'))
    config.merge(CommandLineParser.parse(args) as ConfigObject)

    final ResponseParser responseParser = new ParserFactory().create(config.outputDataFormat as String)
    final HTTPClient httpClient = new HTTPClientFactory().create(config.HTTPClientType as String)
    final URLBuilder urlBuilder = new URLBuilder(config.urlOptions as Map, config.urlPrefix as String)
    final GoogleAPI googleAPI = new GooglePlacesWebAPI(responseParser, httpClient)


    final SearchProcessor processor = new SearcherFactory().create(config)
    final HistoryHolder<Integer, Places> history = new SearchHistoryLIFO<>()

    while (!processor.isSearchFinished(history)) {
        int searchRadius = processor.getNextFactor(history)

        history.
                add(searchRadius,
                        new Places(
                                googleAPI.doGet(urlBuilder.get(searchRadius)),
                                config.excludedTypes as List,
                                config.initialLatitude as double,
                                config.initialLongitude as double))
    }

    Places places = history.getValue(0)
    if (!places.getSize()) {
        places = history.getValue(1) ?: places
    }

    new Response(places, config.maxLocationNumber as int, config.gpsError as int)

} catch (GoogleAPILocatorException e) {
    new Response(e.errorCode, e.getMessage())
}