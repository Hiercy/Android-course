package com.example.volk1.workwithdatabase.roomDB.random_id;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class UniqueID {

    private static Random rnd;

    private static List<Integer> ids = new ArrayList<>();
    private static final int RANGE = 10_000;
    private static int index = 0;

    static {
        for (int i = 0; i < RANGE; i++)
            ids.add(i);
        shuffle(ids);
    }

    public static int getID() {
        if (index > ids.size()-1)
            index = 0;

        return ids.get(index++);
    }

    public static void shuffle(List<?> list) {
        if (rnd == null)
            rnd = new Random();
        shuffle(list, rnd);
    }

    private static void shuffle(List<?> list, Random rand) {
        int size = list.size();

        Object[] arr = list.toArray();

        // Shuffle
        for (int i = size; i > 1; i--)
            swap(arr, i-1, rnd.nextInt(i));
        /*
         * ListIterator - almost the same as Iterator, but
         * can be iterated in either directions
         * works only with List
         */
        ListIterator it = list.listIterator();
        for (int i = 0; i < arr.length; i++) {
            it.next();
            /*
             * Replaces the last element returned by next() or previous() with the specified element (optional operation).
             * This call can be made only if neither remove() nor add(E) have been called after the last call to next or previous.
             */
            it.set(arr[i]);
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    /*
     * Swaps the elements at the specified positions in the specified list.
     * (If the specified positions are equal, invoking this method leaves
     * the list unchanged)
     */
    private static void swap(List<?> arr, int i, int j) {
        final List l = arr;
        l.set(i, l.set(j, l.get(i)));
    }
}
