package com.heiko.placelocator.google

import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.parser.ResponseParser

class GooglePlacesWebAPI implements GoogleAPI {
    ResponseParser responseParser
    HTTPClient httpClient

    GooglePlacesWebAPI(ResponseParser responseParser, HTTPClient httpClient) {
        this.responseParser = responseParser
        this.httpClient = httpClient
    }

    @Override
    List doGet(String url) {
        return responseParser.parse(httpClient.get(url)).results
    }
}