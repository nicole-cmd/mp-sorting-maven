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
    try {
      selectionSort(values);
    } catch (Exception e) {
      // Do nothing.
    } // try/catch
  } // sort(T[])

  // +-----------------+-----------------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Sort an array using selection sort.
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
   * @throws Exception if the array is not of an appropriate length.
   */
  public void selectionSort(T[] values) throws Exception {
    if (values.length <= 1) {
      throw new Exception("Array is not long enough to sort");
    } else if (values.length == 1) {
      return;
    } // if

    int s = 0; // index of our smallest value

    for (int i = 0; i < values.length; i++) {
      if (order.compare(values[i], values[++i]) <= 0) {
        s = i;
      } // if

      Sorter.swap(values, ++i, s);
    } // for
  } // selectionSort(T[])
} // class SelectionSorter
