package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using selection sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Nicole Gorrell (sort method for selection sort)
 */

public class SelectionSorter<T> implements Sorter<T> {
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
  public SelectionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // SelectionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using selection sort.
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
    if (values.length <= 1) {
      return;
    } // if

    int s = 0; // index of our smallest value

    for (int i = 0; i < values.length; i++) {
      if (order.compare(values[i], values[++i]) <= 0) {
        s = i;
      } // if

      swap(values, ++i, s);
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
} // class SelectionSorter
