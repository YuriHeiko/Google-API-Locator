package com.heiko.placelocator

import com.heiko.placelocator.exceptions.GoogleAPILocatorException
import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.http.HTTPClientController
import com.heiko.placelocator.parser.ResponseParserController
import com.heiko.placelocator.search.PlaceSearcher
import com.heiko.placelocator.utils.*

import static com.heiko.placelocator.utils.GoogleAPIChecker.isResponseOK
import static com.heiko.placelocator.utils.Looper.loop

// TODO: find the fringe test cases
// TODO: write JavaDOCs
// TODO: JSON formatter
// TODO: null checks
// TODO: convert incoming coordinates from another format

/**
 * This script uses Google Places Web API to find a nearest possible location
 * by GPS coordinates.
 * @args command line arguments
 * @return nearest possible places ranged from 1 to 5
 */

String result = ''
Map JSON

try {

    // Read initial config from default configuration file
    ConfigObject config = ConfigReader.read()

    // Parse the command line attributes and put them into configuration parameters
    CommandLineParser.parse(args, config)

    // Get parser according to configuration parameters
    final responseParser = ParserBuilder.get(config)

    // Get HTTPClient
    final HTTPClient httpClient = HTTPClientBuilder.get(config)

    // Get PlaceSearcher
    final PlaceSearcher placeSearcher = PlaceSearcherBuilder.get(config)

    // a kind of do-while cycle
    loop {
        // Build and return an appropriate url according to configuration parameters
        final String url = new URLBuilder().getURL(config)

        // Send request and obtain result by the specified url
        final String response = new HTTPClientController(httpClient).get(url)

        // Parse the obtained result into Map
        JSON = new ResponseParserController(responseParser).parse response

        // if a valid response was gotten
        if (isResponseOK(JSON)) {
            // try to find the closest place
            result = placeSearcher.find(JSON.results, config)
        }
    } until { placeSearcher.isResultAchieved() || placeSearcher.changeSearch(config) }

    println result

    return result

} catch (GoogleAPILocatorException e) {
    // TODO: JSON can be empty!
    JSON.put('LocatorAPI error message', e.getMessage())
    return JSON
}