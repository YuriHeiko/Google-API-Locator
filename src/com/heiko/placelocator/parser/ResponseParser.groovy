package com.heiko.placelocator.parser

import com.heiko.placelocator.exceptions.GoogleAPILocatorException
import com.heiko.placelocator.exceptions.InvalidRequestException
import com.heiko.placelocator.exceptions.OverQueryLimitException
import com.heiko.placelocator.exceptions.RequestDeniedException

interface ResponseParser {

    Map parse(final String text)

}
