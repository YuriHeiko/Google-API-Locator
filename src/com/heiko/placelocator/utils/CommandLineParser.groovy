package com.heiko.placelocator.utils

import com.heiko.placelocator.exceptions.GoogleAPILocatorException

/**
 * This is an util class
 */
class CommandLineParser {

    /**
     * This function uses {@code CliBuilder} class in order to parse command line arguments
     * and put them into a config file
     *
     * @param args command line arguments
     * @param config script's {@code ConfigObject}
     * @throws GoogleAPILocatorException if command line arguments are incorrect
     */
    static void parse(String[] args, ConfigObject config) {
        def cli = new CliBuilder(usage: 'locator.groovy -[fhkr] [longitude] [latitude]')

        // Create the list of options.
        cli.with {
            h longOpt: 'help', 'Show usage information'
            f longOpt: 'format', args: 1, argName: 'format', 'Response format can be "json" or "xml"'
            k longOpt: 'key', args: 1, argName: 'key', 'Use the authorization key'
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

        if (options.f) {
            config.format = options.format
        }

        if (options.k) {
            config.authorizationKey = options.key
        }

        if (options.r) {
            try {
                config.radius = Integer.parseInt(options.radius)
            } catch (NumberFormatException e) {
                throw new GoogleAPILocatorException('Incorrect command line arguments. ' +
                        'Radius must be an integer')
            } finally {
                if (config.radius < 1) {
                    throw new GoogleAPILocatorException('Incorrect command line arguments. ' +
                            'Radius must be greater than 0')
                }
            }
        }

        // Handle all non-option arguments.
        def extraArguments = options.arguments()

        if (extraArguments.size() == 2) {
            try {
                config.longitude = Double.parseDouble(extraArguments[0])
                config.latitude = Double.parseDouble(extraArguments[1])
            } catch (NumberFormatException e) {
                throw new GoogleAPILocatorException('Incorrect command line arguments. [longitude] and ' +
                        '[latitude] must be a double number')
            }

        } else {
            throw new GoogleAPILocatorException('Incorrect command line arguments. ' +
                    '[longitude] and [latitude] are obligatory')
        }
    }
}
