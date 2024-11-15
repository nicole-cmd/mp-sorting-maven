package edu.grinnell.csc207.sorting;

import edu.grinnell.csc207.util.ArrayUtils;

import java.util.Comparator;

/**
 * Something that fails to sort.  Intended primarily to allow us to watch
 * tests fail.
 *
 * @param <T>
 *   Some type. Ignored.
 *
 * @author Samuel A. Rebelsky
 */

public class FakeSorter<T> implements Sorter<T> {

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter.
   */
  public FakeSorter() {
  } // FakeSorter()

  /**
   * Create a sorter using a particular comparator (included for
   * consistency with other sorters).
   *
   * @param order
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public FakeSorter(Comparator<? super T> order) {
  } // FakeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place.
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
    // Randomly permute the elements of the list and hope that that
    // works.
    ArrayUtils.permute(values);
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
} // class FakeSorter
