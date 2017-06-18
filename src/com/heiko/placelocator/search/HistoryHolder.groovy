package com.heiko.placelocator.search

interface HistoryHolder<T, U> {

    void put(T t, U u)

    T getKey(int index)

    U getValue(int index)

    int getSize()
}
