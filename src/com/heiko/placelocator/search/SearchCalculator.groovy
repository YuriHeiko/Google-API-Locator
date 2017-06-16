package com.heiko.placelocator.search

import com.heiko.placelocator.location.Places

/**
 * Contains {@link TargetPlaceSearcher} algorithm logic
 */
class SearchCalculator {
    private int maxIterationsCounter
    private int tendency
    private int newTendency
    private int radius
    private int rate
    private int iterationsCounter
    private int possibleLocationsNumber

    /**
     * Constructs a new object and sets initial fields parameters
     *
     * @param maxIterationsCounter the maximum number of search iterations
     * @param radius the initial search radius
     * @param rate the initial rate of radius changes
     */
    SearchCalculator(int maxIterationsCounter, int possibleLocationsNumber, int radius, int rate) {
        this.maxIterationsCounter = maxIterationsCounter
        this.radius = radius
        this.rate = rate
        this.possibleLocationsNumber = possibleLocationsNumber
        iterationsCounter = maxIterationsCounter
    }

    /**
     * Calculates the new radius according to the search tendency changes and rate.
     * Generally, if the tendency is negative shrinks the radius. Expands it
     * otherwise. The rate logic is to expand twofold slower then shrink, and the
     * expand rate cannot be greater then initial one.
     *
     * @return the new radius
     */
    int calculateRadius() {
        if (newTendency > 0) {
            radius *= rate / (tendency < 0 ? 2 : 1)
        } else {
            rate /= 2
            radius /= rate
        }
        tendency = newTendency

        radius
    }

    /**
     * Checks if found locations are the closest or it was the last search iteration.
     * <p><ul>
     *     <li>If there are no locations found, sets the positive search tendency returns false.
     *     <li>If it is not the first search iteration and it is found more then
     *     possibleLocationsNumber, sets the negative search tendency and returns false.
     *     <li>Returns true, in the other cases.
     * </ul><p>
     *
     * @param places a {@code Places} object contains found locations
     * @return true if search is finished
     */
    boolean check(Places places) {
        boolean isSearchFinished = true

        if (--iterationsCounter > 0) {
            if (places.getSize() == 0) {
                newTendency = 1
                isSearchFinished = false
            } else if (places.getSize() > possibleLocationsNumber && iterationsCounter < maxIterationsCounter - 1) {
                newTendency = -1
                isSearchFinished = false
            }
        }

        isSearchFinished
    }
}