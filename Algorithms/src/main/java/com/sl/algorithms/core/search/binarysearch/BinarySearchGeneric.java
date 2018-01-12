package com.sl.algorithms.core.search.binarysearch;

import com.sl.algorithms.core.interfaces.search.Search;

import static com.sl.algorithms.core.utils.Formulas.midPoint;

/**
 * <br>{@link BinarySearchGeneric} is useful for both regular and rotated array.<br>
 * <br><u>Related Problems</u>:
 * <br><a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/">Find Minimum in a Rotated Sorted Array</a>
 * <br><a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/description/">Find Minimum in a Rotated Sorted Array (with duplicates)</a>
 * <br><a href="https://leetcode.com/problems/search-in-rotated-sorted-array/description/">Search in Rotated Sorted Array</a>
 * <br><a href="https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/">Search in Rotated Sorted Array (with duplicates)</a>
 */
public class BinarySearchGeneric<T extends Comparable> implements Search<T> {

    @Override
    public int findIndex(T[] rotatedSortedInput, T targetElement) {
        objChecks(rotatedSortedInput);
        int start = 0, end = rotatedSortedInput.length - 1;
        int startOfAscent = rotatedSortedInput.length == 1 ? 0 : findStartOfAscent(rotatedSortedInput, start, end);
        return findIndexGeneric(rotatedSortedInput, targetElement, start, end, startOfAscent);
    }

    @SuppressWarnings("unchecked") // compareTo
    private int findIndexGeneric(T[] a, T target, int start, int end, int startOfAscent) {
        while (start <= end) {
            int midPoint = midPoint(start, end);
            int realMidPoint = (midPoint + startOfAscent) % a.length;
            int diff = a[realMidPoint].compareTo(target);
            if (diff == 0) {
                return realMidPoint;
            }
            if (diff < 0) {
                start = midPoint + 1;
            } else {
                end = midPoint - 1;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    public int findStartOfAscent(T[] a) {
        objChecks(a);
        return a.length == 1 ? 0 : findStartOfAscent(a, 0, a.length - 1);
    }

    @SuppressWarnings("unchecked") // compareTo
    private int findStartOfAscent(T[] a, int start, int end) {
        while (start < end && a[start].compareTo(a[end]) >= 0) { // both checks are necessary e.g. when all elements are the same or when all elements are same except one (e.g. [1,3,1,1,1])
            int m = midPoint(start, end);
            int mDiff = a[m].compareTo(a[end]);
            if (mDiff > 0) { // start-index is at the right
                start = m + 1;
            } else if (mDiff < 0) { // start-index is at the left
                end = m;
            } else { // mDiff == 0 // this is to handle duplicates
                end--;
                if (a[start].compareTo(a[end]) < 0) { // this is to handle sparsed array e.g. [1,1,1,2,1,1]
                    return ++end;
                }
            }
        }
        return start;
    }
}
