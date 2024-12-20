package edu.grinnell.csc207.sorting;

/**
 * Things that know how to sort arrays of values.
 *
 * @author Samuel A. Rebelsky
 * @author Nicole Gorrell - swap method declaration
 *
 * @param <T>
 *   The type of value in the array.
 */
public interface Sorter<T> {
  // +---------+----------------------------------------------
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
  public void sort(T[] values);

  // +----------------+----------------------------------------------
  // | Static methods |
  // +----------------+

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
  public static <T> void swap(T[] arr, int i1, int i2) {
    // temporarily stores a value to swap with the other
    T temp = null;
  
    temp = arr[i2];
    arr[i2] = arr[i1];
    arr[i1] = temp;
  } // swap(T[], int, int)
} // interface Sorter<T>

