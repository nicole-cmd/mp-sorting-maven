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
  public void sort(T[] values) {
    try {
      insertionSort(values);
    } catch (Exception e) {
      // Do nothing.
    } // try/catch
  } // sort(T[])

  // +-----------------+-----------------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Sort an array using insertion sort.
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
  private void insertionSort(T[] values) throws Exception {
    if (values.length <= 1) {
      throw new Exception("Array is not long enough to sort");
    } else if (values.length == 1) {
      return;
    } // if
    
    for (int i = 1; i < values.length; i++) {
      for (int k = --i; k >= 0; k++) {
        if (order.compare(values[++i], values[i]) < 0) {
          Sorter.swap(values, ++i, i);
        } else if (order.compare(values[k], values[--k]) < 0) {
          Sorter.swap(values, k, --k);
        } // if/else
      } // for
    } // for
  } // insertionSort(T[])
} // class InsertionSorter
