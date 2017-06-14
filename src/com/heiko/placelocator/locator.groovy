package com.heiko.placelocator

import com.heiko.placelocator.exceptions.GoogleAPILocatorException
import com.heiko.placelocator.location.Places
import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.http.HTTPClientBuilder
import com.heiko.placelocator.http.URLBuilder
import com.heiko.placelocator.parser.ParserBuilder
import com.heiko.placelocator.search.PlaceSearcher
import com.heiko.placelocator.search.PlaceSearcherBuilder
import com.heiko.placelocator.utils.CommandLineParser
import com.heiko.placelocator.utils.ConfigReader

import static com.heiko.placelocator.utils.GoogleAPIChecker.isResponseOK
import static com.heiko.placelocator.utils.Looper.loop

// TODO: find the fringe test cases
// TODO: write JavaDOCs
// TODO: JSON formatter
// TODO: null checks
// TODO: convert incoming coordinates from another format

/**
 * Uses Google Places Web API to find a nearest possible location
 * by GPS coordinates.
 *
 * @args command line arguments
 * @return nearest possible places ranged from 1 to 5
 */

Map result = [status: "ERROR"] // default response

try {
    // Read the initial configuration from the default configuration file, parse
    // command line arguments and put them into the initial configuration object
    final ConfigObject config = CommandLineParser.parse(args, ConfigReader.read())

    // Get parser according to configuration parameters
    final responseParser = new ParserBuilder().get(config)

    // Get HTTPClient
    final HTTPClient httpClient = new HTTPClientBuilder().get(config)

    // Get PlaceSearcher
    final PlaceSearcher placeSearcher = new PlaceSearcherBuilder().get(config)

    // a kind of a do-while cycle
    loop {
        // Build and return an appropriate url according to configuration parameters
        final String url = new URLBuilder().getURL(config)

        // Send request and obtain result by the specified url
        final String response = httpClient.get(url)

        // Parse the obtained result into Map
        Map parsedResponse = responseParser.parse(response) as Map

        // if a valid response was gotten
        if (isResponseOK(parsedResponse.status as String)) {

            Places places = new Places(
                    parsedResponse.results as List,
                    config.excludedTypes as List,
                    config.latitude as double,
                    config.lonitude as double)

            // try to find the closest place
            result = placeSearcher.find(parsedResponse.results as List, config) as Map
        }
    } until { placeSearcher.isResultAchieved() || placeSearcher.changeSearch(config) }

    println result

    return result

} catch (GoogleAPILocatorException e) {
    result.put('LocatorAPI error message', e.getMessage())

    println result

    return result
}