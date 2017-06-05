package ru.matevosyan;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;


/**
 * FastSimpleSetTest class.
 * Created on 04.06.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class FastSimpleSetTest {

    /**
     * Create whenCreateTwoObjectAndAddValuesThanCheckWhoAddedFasterToTheContainer() to check hwo is faster.
     */

    @Test public void whenCreateTwoObjectAndAddValuesThanCheckWhoAddedFasterToTheContainer() {
        FastSimpleSet<Integer> fastSimpleSet = new FastSimpleSet<>();
        DynamicSimpleSet<Integer> dynamicSimpleSet = new DynamicSimpleSet<>();

        long fastSetTimeBegin = System.currentTimeMillis();
        for (int i = 0; i < 3223; i++) {
            fastSimpleSet.add(i);
        }
        long fastSetTimeEnd = System.currentTimeMillis();


        long dynamicSimpleSetTimeBegin = System.currentTimeMillis();
        for (int i = 0; i < 3223; i++) {
            dynamicSimpleSet.add(i);
        }
        long dynamicSimpleSetTimeEnd = System.currentTimeMillis();

        long fastTime = fastSetTimeEnd - fastSetTimeBegin;
        long dynamicSimpleTime = dynamicSimpleSetTimeEnd - dynamicSimpleSetTimeBegin;

        assertTrue(fastTime < dynamicSimpleTime);


    }

    /**
     * Create whenAddValueToTheFastSimpleSetThanCheckIt() to check value.
     */

    @Test
    public void whenAddValueToTheFastSimpleSetThanCheckIt() {
        FastSimpleSet<Integer> fastSimpleSet = new FastSimpleSet<>();
        fastSimpleSet.add(5);

        assertThat(fastSimpleSet.get(0), is(5));
    }

}