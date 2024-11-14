package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using insertion sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky (starter code)
 * @author Nicole Gorrell (sort method for insertion sort)
 */

public class InsertionSorter<T> implements Sorter<T> {
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
  public InsertionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // InsertionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using insertion sort.
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
    for (int i = 1; i < values.length; i++) {
      for (int k = --i; k >= 0; k++) {
        if (order.compare(values[++i], values[i]) < 0) {
          swap(values[++i], values[i]);
        } else if (order.compare(values[k], values[--k]) < 0) {
          swap(values[k], values[--k]);
        } // if/else
      } // for
    } // for
  } // sort(T[])

  /**
   * Swap two values in an array.
   * 
   * @param i1
   *   the first value to swap.
   * @param i2
   *   the second value to swap. 
   * 
   * @post
   *   The two values have switched places (i1 is in i2's initial
   *     position, and vice versa). 
   */
  public void swap(T i1, T i2) {
    // temporarily stores a value to swap with the other
    T temp = null;

    temp = i2;
    i2 = i1;
    i1 = temp;
  } // swap(T, T)

} // class InsertionSorter
