package com.heiko.placelocator.initializers

import com.heiko.placelocator.exceptions.GoogleAPILocatorException

class CommandLineParser {

    /**
     * Uses {@code CliBuilder} class in order to parse command line arguments
     * and put them into a config file
     *
     * @param args command line arguments
     * @param config script's {@code ConfigObject}
     * @throws GoogleAPILocatorException if command line arguments are incorrect
     */
    static ConfigObject parse(final String[] args, ConfigObject config) {
        def cli = new CliBuilder(usage: 'locator.groovy -[dfhkr] [latitude] [longitude]')

        // Create the list of options.
        cli.with {
            h longOpt: 'help', 'Show usage information'
            d longOpt: 'do-not-filter-by-types', 'Turn off filtering by types'
            f longOpt: 'format', args: 1, argName: 'format', 'Response format can be "json" or "xml"'
//            k longOpt: 'key', args: 1, argName: 'key', 'Use the authorization key'
            r longOpt: 'radius', args: 1, argName: 'radius', 'Use the radius'
        }

        def options = cli.parse(args)
        if (!options) {
            return
        }

        // Show usage text when -h or --help option is used.
        if (options.h) {
            cli.usage()
            return
        }

        if (options.d) {
            if (config.types)
                config.excludedTypes = new ArrayList<>()
        }

        if (options.f) {
            config.format = options.format
        }

        if (options.k) {
            config.authorizationKey = options.key
        }

        // Handle all non-option arguments.
        def extraArguments = options.arguments()
        if (extraArguments.size() == 2) {

            try {
                double latitude = Double.parseDouble(extraArguments[0])
                double longitude = Double.parseDouble(extraArguments[1])

                config.urlOptions.put("location", "$latitude,$longitude")

                config.latitude = latitude
                config.longitude = longitude

            } catch (NumberFormatException e) {
                throw new GoogleAPILocatorException('Incorrect command line arguments. ' +
                        '[latitude] and [longitude] must be double numbers')
            }

        } else {
            throw new GoogleAPILocatorException('Incorrect command line arguments. ' +
                    '[latitude] and [longitude] are obligatory')
        }

        return config
    }
}
