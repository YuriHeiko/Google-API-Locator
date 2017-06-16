package com.heiko.placelocator

// initial
HTTPClientType = 'simple'
placeSearcherType = 'Target searcher'
maxIterationNumber = 4
maxLocationNumber = 5
searchRadius = 14
searchRate = 6
possibleLocationsNumber = 10

// API configuration
inputDataFormat = 'json'
authorizationKey = 'AIzaSyBCdeSMg1PnVaAOaEVrB_GIog-q6fs27Fw'
urlPrefix = "https://maps.googleapis.com/maps/api/place/nearbysearch/$inputDataFormat?"
urlOptions = [key: authorizationKey, radius: searchRadius]

// filter
excludedTypes = ['administrative_area_level_1',
                 'administrative_area_level_2',
                 'administrative_area_level_3',
                 'administrative_area_level_4',
                 'administrative_area_level_5',
                 'colloquial_area',
                 'country',
                 'finance',
                 'floor',
                 'general_contractor',
                 'geocode',
                 'locality',
                 'neighborhood',
                 'political',
                 'post_box',
                 'postal_code',
                 'postal_code_prefix',
                 'postal_code_suffix',
                 'postal_town',
                 'room',
                 'route',
                 'street_address',
                 'street_number',
                 'sublocality',
                 'sublocality_level_4',
                 'sublocality_level_5',
                 'sublocality_level_3',
                 'sublocality_level_2',
                 'sublocality_level_1',
                 'atm',
                 'moving_company',
                 'painter',
                 'physiotherapist',
                 'roofing_contractor',
                 'taxi_stand']
