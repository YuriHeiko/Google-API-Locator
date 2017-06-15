package com.heiko.placelocator.search

import com.heiko.placelocator.http.URLBuilder

class RadiusChanger {
    private int tendency
    private URLBuilder urlBuilder
    private int radius
    private int rate

    RadiusChanger(URLBuilder urlBuilder, int rate) {
        this.urlBuilder = urlBuilder
        this.radius = urlBuilder.getOption("radius") as int
        this.rate = rate
    }

    void change(int newTendency) {
        if (tendency > 0) {
            radius *= rate / (tendency < 0 && newTendency > 0 ? 2 : 1)
        } else {
            rate /= 2
            radius /= rate
        }
        tendency = newTendency

        urlBuilder.setOption("radius", radius)
    }
}