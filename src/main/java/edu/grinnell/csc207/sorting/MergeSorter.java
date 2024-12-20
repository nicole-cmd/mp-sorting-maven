package edu.grinnell.csc207.sorting;

import java.util.Arrays;
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
    try {
      mergeSort(values);
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
  public void mergeSort(T[] values) throws Exception {
    if (values.length <= 1) {
      throw new Exception("Array is not long enough to sort");
    } else if (values.length == 1) {
      return;
    } // if

    int middle = values.length / 2;
    T[] secondHalf = Arrays.copyOfRange(values, middle, values.length);

    for (int i = 0; i < values.length; i++) {
      if (i < middle) {
        if (order.compare(values[++i], values[i]) < 0) {
          Sorter.swap(values, ++i, i);
        } // if

        mergeSort(values);
      } // if

      mergeSort(secondHalf);
    } // for

    // pointer to keep track of positions in the first split array
    int track1 = 0;

    @SuppressWarnings({"unchecked"})
    T[] newArray = (T[]) new Object[values.length];

    for (int i = 0; i < newArray.length; i++) {
      if (order.compare(values[track1], values[middle]) < 0) {
        newArray[i] = values[track1];
        track1++;
      } else {
        newArray[i] = values[middle];
        middle++;
      } // if/else
    } // for
    
    // to remain compatible with Sorter interface, manipulate
    // original array
    values = newArray;
  } // mergeSort(T[])
} // class MergeSorter
