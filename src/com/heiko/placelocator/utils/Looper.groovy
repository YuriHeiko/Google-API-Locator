package com.heiko.placelocator.utils

/**
 * This is an util class
 * Adds kinda do/while cycle into the groovy language
 */
class Looper {
    private Closure code

    static Looper loop(Closure code) {
        new Looper(code: code)
    }

    void until(Closure test) {
        code()
        while (!test()) {
            code()
        }
    }
}