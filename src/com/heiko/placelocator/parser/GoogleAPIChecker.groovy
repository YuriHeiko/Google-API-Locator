package com.heiko.placelocator.parser

import com.heiko.placelocator.exceptions.GoogleAPILocatorException
import com.heiko.placelocator.exceptions.InvalidRequestException
import com.heiko.placelocator.exceptions.OverQueryLimitException
import com.heiko.placelocator.exceptions.RequestDeniedException

trait GoogleAPIChecker {

    /**
     * Checks Google Places API response status
     *
     * @param response {@code Map} that contains parsed JSON response
     * @return true if status is 'OK' or 'ZERO_RESULTS'
     * @throws OverQueryLimitException if query limit is exceeded
     * @throws RequestDeniedException if Google API denies a request
     * @throws InvalidRequestException if a request has invalid format
     * @throws GoogleAPILocatorException if The Google Places API probably changes
     */
    Map checkResponse(Map response) {

        switch (response.status) {

            case 'OK':
            case 'ZERO_RESULTS':
                break

            case 'OVER_QUERY_LIMIT':
                throw new OverQueryLimitException('Query limit has been exceeded!')

            case 'REQUEST_DENIED':
                throw new RequestDeniedException('Google API has denied your request! ' +
                                                'Generally because of lack of an invalid key parameter')

            case 'INVALID_REQUEST':
                throw new InvalidRequestException('Invalid request format. Generally indicates that' +
                                                'a required query parameter (location or radius) is missing')

            default:
                throw new GoogleAPILocatorException('The Google Places API has probably changed!')
        }

        response
    }
}