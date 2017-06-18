package com.heiko.placelocator.search

interface SearchHistory<T, U> {

    void put(T t, U u)

    T getKey(int index)

    U getValue(int index)

    int getSize()
}
