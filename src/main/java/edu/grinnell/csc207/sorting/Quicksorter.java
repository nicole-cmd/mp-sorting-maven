package edu.grinnell.csc207.sorting;

import java.util.Arrays;
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
    try {
      partition(values);
    } catch (Exception e) {
      // Do nothing.
    } // try/catch
  } // sort(T[])

  // +-----------------+-----------------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Sort an array using Quicksort.
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
  private void partition(T[] values) throws Exception {
    if (values.length <= 1) {
      throw new Exception("Array is not long enough to sort");
    } else if (values.length == 1) {
      return;
    } // if

    // setting our bounds and finding pivot
    int sm = 0;
    int lg = values.length;
    int pivot = findPivot(lg);

    Sorter.swap(values, pivot, 0);
    sm++;

    int i = 0;
    while (sm != lg) {
      if (order.compare(values[i], values[++i]) < 0) {
        Sorter.swap(values, ++i, lg - 1);
        lg =- 1;
      } else if (order.compare(values[i], values[++i]) >= 0) {
        sm++;
      } // if
    } // while
    
    // could be sm or lg by this point, they will be equal
    pivot = sm;
    Sorter.swap(values, pivot, 0);
    T[] firstHalf = Arrays.copyOfRange(values, 0, pivot);
    T[] secondHalf = Arrays.copyOfRange(values, pivot, values.length);
    partition(firstHalf);
    partition(secondHalf);
  } // partition(T[])

  /**
   * Find a pivot point in our array to save for the final swap.
   * 
   * @param limit
   *   The bound for our randomly generated pivot to fall within
   *   the array length.
   *
   * @return the determined pivot index of the array.
   */
  private int findPivot(int limit) {
    int v1 = generateRand(limit);
    int v2 = generateRand(limit);
    int v3 = generateRand(limit);

    int pivot = 0;

    // set pivot by finding the median randomly-generated value
    if (v1 < v2 && v1 < v3) {
      pivot = v1;
    } else if (v2 < v1 && v2 < v3) {
      pivot = v2;
    } else {
      pivot = v3;
    } // if/else
    return pivot;
  } // findPivot(int)

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
