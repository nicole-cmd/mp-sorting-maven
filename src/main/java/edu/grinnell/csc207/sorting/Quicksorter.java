package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Nicole Gorrell (sort method for incomplete Quicksort)
 */

public class Quicksorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    // track the small and large portions of the array
    int small = 0;
    int large = values.length;

    // setting our bounds
    int lb = small;
    int ub = large;

    int v1 = generateRand(ub);
    int v2 = generateRand(ub);
    int v3 = generateRand(ub);

    int pivot = 0;

    // set pivot
    if (v1 < v2 && v1 < v3) {
      pivot = v1;
    } else if (v2 < v1 && v2 < v3) {
      pivot = v2;
    } else {
      pivot = v3;
    } // if/else

    swap(values, pivot, 0);
    small++;

    for (int i = small; i < values.length; i++) {
      if (order.compare(values[i], values[++i]) < 0) {
        swap(values, ++i, values.length - 1);
        large =- 1;
      } else if (order.compare(values[i], values[++i]) >= 0) {
        small++;
      } // if
    } // for
  } // sort(T[])

  /**
   * Swap two values in an array.
   * 
   * @param arr
   *   the array in which we're swapping.
   * @param i1
   *   the index of the first value to swap.
   * @param i2
   *   the index of the second value to swap. 
   * 
   * @post
   *   The two values have switched places (i1 is in i2's initial
   *     position, and vice versa). 
   */
  public void swap(T[] arr, int i1, int i2) {
    // temporarily stores a value to swap with the other
    T temp = null;

    temp = arr[i2];
    arr[i2] = arr[i1];
    arr[i1] = temp;
  } // swap(T, T)

  /**
   * Generate a random integer from 0 to the array's upper bound.
   * 
   * @param upperbound
   *   the (exclusive) maximum number the randomly generated
   *   integer can be.
   * 
   * @return a randomly generated number within the desired
   *         range.
   */
  private int generateRand(int upperbound) {
    Random rand = new Random();

    return rand.nextInt(upperbound);
  } // generateRand(int)

} // class Quicksorter
