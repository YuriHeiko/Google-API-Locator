package com.heiko.placelocator.search2

interface SearchHistory<T, U> {

    void add(T t, U u)

    T get(int index)

    T getLast()

    T getBeforeLast()

    int getSize()
}
