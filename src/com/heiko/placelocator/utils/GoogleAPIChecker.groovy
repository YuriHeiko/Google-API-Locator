package com.heiko.placelocator.utils

import com.heiko.placelocator.exceptions.GoogleAPILocatorException
import com.heiko.placelocator.exceptions.InvalidRequestException
import com.heiko.placelocator.exceptions.OverQueryLimitException
import com.heiko.placelocator.exceptions.RequestDeniedException

/**
 * This is an util class
 */
class GoogleAPIChecker {

    /**
     * Checks Google Places API response status
     *
     * @param response {@code Map} that contains parsed JSON response
     * @return true if status is 'OK' or 'ZERO_RESULTS', throws an exception otherwise
     * @throws OverQueryLimitException if query limit is exceeded
     * @throws RequestDeniedException if Google API denies a request
     * @throws InvalidRequestException if a request has invalid format
     * @throws GoogleAPILocatorException if The Google Places API probably changes
     */
    static boolean isResponseOK(Map response) {
        if (response && response.size() != 0) {
            switch (response.status) {

                case 'OK':
                    return true

                case 'ZERO_RESULTS':
                    return true

                case 'OVER_QUERY_LIMIT':
                    throw new OverQueryLimitException('Query limit has been exceeded!')

                case 'REQUEST_DENIED':
                    throw new RequestDeniedException('Google API has denied your request! ' +
                            'Generally because of lack of an invalid key parameter')

                case 'INVALID_REQUEST':
                    throw new InvalidRequestException('Invalid request format. Generally indicates that' +
                            ' a required query parameter (location or radius) is missing')
            }
        }

        throw new GoogleAPILocatorException('The Google Places API has probably changed!')
    }
}