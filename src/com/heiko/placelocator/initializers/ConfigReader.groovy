package com.heiko.placelocator.initializers

/**
 * Uses a {@code ConfigSlurper} class to reads property file (groovy script)
 * and returns a {@code ConfigObject} instance.
 */
class ConfigReader {

    final static String DEFAULT_FILE_NAME = 'Properties.groovy'

    /**
     * Reads properties from the default groovy script file.
     *
     * @return The ConfigObject instance
     */
    static Map read() {
        read(new File(DEFAULT_FILE_NAME))
    }

    /**
     * Reads properties from a specified url and fills the property map.
     * Uses {@code ConfigSlurper} class.
     *
     * @param location The original location of the Script as a URL
     * @return The ConfigObject instance
     */
    static Map read(URL location) {
        new ConfigSlurper().parse(location)
    }

    /**
     * Reads properties from the specified groovy script file.
     *
     * @return The ConfigObject instance
     */
    static Map read(File file) {
        read(file.toURI().toURL())
    }
}
