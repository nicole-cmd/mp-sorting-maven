package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Nicole Gorrell (sort method for merge sort)
 */

public class MergeSorter<T> implements Sorter<T> {
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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
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

    int middle = values.length / 2;

    for (int i = 0; i < values.length; i++) {
      if (i < middle) {
        if (order.compare(values[++i], values[i]) < 0) {
          swap(values, ++i, i);
        } // if

        sort(values);
      } // if

      sort(values);
    } // for

    // pointers to keep track of positions in the split array
    int track1 = 0;
    int track2 = middle;

    @SuppressWarnings({"unchecked"})
    T[] newArray = (T[]) new Object[values.length];

    for (int i = 0; i < newArray.length; i++) {
      if (order.compare(values[track1], values[track2]) < 0) {
        newArray[i] = values[track1];
        track1++;
      } else {
        newArray[i] = values[track2];
        track2++;
      } // if/else
    } // for
    
    /** to remain compatible with Sorter interface, manipulate
     *  original array */
    values = newArray;
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

} // class MergeSorter
