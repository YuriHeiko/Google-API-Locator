package com.heiko.placelocator.initializers

class Initializer {

    static ConfigObject getConfiguration(String[] args) {
        CommandLineParser.parse(args, ConfigReader.read())
    }
}
