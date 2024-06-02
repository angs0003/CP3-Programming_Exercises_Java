package cp3.lab04.expandablearray;

import java.util.Arrays;

public class UnsafeExpandableArray {
    private int[] array;
    private int size;

    public UnsafeExpandableArray(int initialCapacity) {
        array = new int[initialCapacity];
        size = 0;
    }

    public void add(int value) {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[size++] = value; // This is not thread-safe
    }

    public int size() {
        return size;
    }

    public int get(int index) {
        return array[index];
    }
}
