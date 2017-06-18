package com.heiko.placelocator.search.Impl

import com.heiko.placelocator.location.Places
import com.heiko.placelocator.search.HistoryHolder

class SearchHistoryLIFO implements HistoryHolder<Integer, Places> {
    private final Map<Integer, Places> map = new LinkedHashMap<>()

    @Override
    void add(Integer radius, Places places) {
        map.put(radius, places)
    }

    @Override
    Integer getKey(int index) {
        map.keySet()[-(++index)]
    }

    @Override
    Places getValue(int index) {
        map.get(map.keySet()[-(++index)])
    }

    @Override
    int getSize() {
        return map.size()
    }

    @Override
    void put(Integer radius, Places places) {
        map.put(radius, places)
    }
}
