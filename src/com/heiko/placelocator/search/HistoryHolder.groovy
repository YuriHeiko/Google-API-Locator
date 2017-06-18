package com.heiko.placelocator.search

interface HistoryHolder<T, U, S> {

    void put(T t, U u, S s)

    T getKey(int index)

    U getFirstValue(int index)

    S getSecondValue(int index)

    void setFirstValueByKey(T t, U u)

    void setSecondValueByKey(T t, S s)

    int getSize()
}
