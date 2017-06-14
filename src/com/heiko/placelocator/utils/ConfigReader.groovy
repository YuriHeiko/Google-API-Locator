package com.heiko.placelocator.utils
/**
 * This class uses a {@code ConfigSlurper} class to reads property file (groovy script)
 * and returns a {@code ConfigObject} instance.
 */
class ConfigReader {

    /**
     * This is an overloaded function that reads properties from the default groovy script file
     *
     * @return The ConfigObject instance
     */
    static Map read() {
        File defaultFile = new File('Properties.groovy')
        read(defaultFile)
    }

    /**
     * This function reads properties from a specified url and fills the property map
     *
     * @param location The original location of the Script as a URL
     * @return The ConfigObject instance
     */
    static Map read(URL location) {
        ConfigSlurper slurper = new ConfigSlurper()
        slurper.parse(location)
    }

    /**
     * This is an overloaded function that reads properties from the specified groovy script file
     *
     * @return The ConfigObject instance
     */
    static Map read(File file) {
        read(file.toURI().toURL())
    }
}
