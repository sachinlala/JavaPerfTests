package com.sl.algorithms.core.sorting.finitegroups;

import com.sl.algorithms.core.interfaces.sorting.SortingEngine;
import com.sl.algorithms.core.sorting.generalpurpose.SortingEngineTest;
import org.junit.Assert;
import org.junit.Test;

import static com.sl.algorithms.core.utils.ArrayOps.printArray;

public class PolishNationalFlagsSortTest extends SortingEngineTest {
    private static SortingEngine<Integer> integerSortingEngine;
    private static SortingEngine<String> stringSortingEngine;

    @Test
    public void testPNFSort1c() {
        integerSortingEngine = new PolishNationalFlagSort<>(0);
        stringSortingEngine = new PolishNationalFlagSort<>("A");
        baseTests(integerSortingEngine);
        testPNFSort();
    }

    @Test
    public void testPNFSort2c() {
        integerSortingEngine = new PolishNationalFlagSort<>(0, 1);
        stringSortingEngine = new PolishNationalFlagSort<>("A", "B");
        baseTests(integerSortingEngine);
        testPNFSort();
    }

    public void testPNFSort() {
        {
            Integer[] sampleNumbers = new Integer[]{1, 0, 1, 0, 1, 0, 0, 1, 0};
            integerSortingEngine.sort(sampleNumbers);
            Assert.assertEquals("[0,0,0,0,0,1,1,1,1]", printArray(sampleNumbers));
        }
        { // add a 3rd color
            Integer[] sampleNumbers = new Integer[]{1, 0, 1, 0, 2, 2};
            integerSortingEngine.sort(sampleNumbers);
            Assert.assertEquals("[0,0,1,1,2,2]", printArray(sampleNumbers)); // white i.e. 0 is in the beginning, everything else is after that; it's incidental to this data-set that overall sorting is achieved
        }
        {
            String[] sampleData = new String[]{"A", "A", "B", "A", "A", "B", "B", "B"};
            stringSortingEngine.sort(sampleData);
            Assert.assertEquals("[A,A,A,A,B,B,B,B]", printArray(sampleData));
        }
        { // add 3rd color
            String[] sampleData = new String[]{"A", "A", "B", "A", "A", "C", "C", "B", "B", "B"};
            stringSortingEngine.sort(sampleData);
            Assert.assertEquals("[A,A,A,A,B,C,C,B,B,B]", printArray(sampleData)); // white i.e. A is in the beginning, everything else is after that
        }
    }
}
