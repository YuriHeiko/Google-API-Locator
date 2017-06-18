package com.heiko.placelocator.search.Impl

import com.heiko.placelocator.location.Places
import com.heiko.placelocator.search.HistoryHolder

class SearchHistoryLIFO implements HistoryHolder<Integer, Places, Integer> {
    private final Map<Integer, List> map = new LinkedHashMap<>()

    @Override
    void put(Integer radius, Places places, Integer tendency) {
        map.put radius, new ArrayList(Arrays.asList(places, tendency))
    }

    @Override
    Integer getKey(int index) {
        map.keySet()[-(++index)]
    }

    @Override
    Places getFirstValue(int index) {
        !map.keySet()[-(++index)] ? null : map.values()[-(++index)][0]
    }

    @Override
    Integer getSecondValue(int index) {
        map.values()[-(++index)][1]
    }

    @Override
    void setFirstValueByKey(Integer radius, Places places) {
        map.put(radius, new ArrayList(Arrays.asList(places, map.get(radius)[1])))
    }

    @Override
    void setSecondValueByKey(Integer radius, Integer tendency) {
        map.put(radius, new ArrayList(Arrays.asList(map.get(radius)[0], tendency)))
    }

    @Override
    int getSize() {
        return map.size()
    }
}
