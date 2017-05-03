package ru.matevosyan;

/**
 * EvenNumber class.
 * Created on 03.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class EvenNumber implements ArrayIterator {

    private int index = 0;
    private final int[] array;

    /**
     * DimensionalIterator constructor.
     * @param array two-dimensional array.
     */

    public EvenNumber(int[] array) {
        this.array = array;
    }

    /**
     * Override the next() method to iterate each element from two-dimensional array.
     * @return arrays element value.
     */

    @Override
    public int[] next() {
        int[] value = new int[0];
        for (int i = 0; array[index++] % 2 == 0; i++){
            value[i++] = array[index];
        }
        return  value;
    }

    /**
     * Override hasNext() method to check if eny of elements are in array to read, looking to next() pointer.
     * @return true if there are any elements to read, else return false.
     */

    @Override
    public boolean hasNext() {
        return array.length > index;
    }
}
