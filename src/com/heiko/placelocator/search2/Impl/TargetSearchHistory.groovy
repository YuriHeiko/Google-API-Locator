package com.heiko.placelocator.search2.Impl

import com.heiko.placelocator.location.Places
import com.heiko.placelocator.search2.SearchHistory

class TargetSearchHistory implements SearchHistory<Places, Integer> {
    private final Map<Integer, Places> map = new LinkedHashMap<>();
    private int radiusLast
    private int radiusBeforeLast

    @Override
    void add(Places places, Integer radius) {
        map.put(radius, places)
    }

    @Override
    Places get(int index) {
        Places places = null

        for (Map.Entry<Integer, Places> entry : map.entrySet()) {
            if (--index == 0) {
                places = entry.getValue()
            }
        }

        places
    }

    @Override
    Places getLast() {
        map.get(radiusLast)
    }

    @Override
    Places getBeforeLast() {
        map.get(radiusBeforeLast)
    }

    @Override
    int getSize() {
        return map.size()
    }
}
