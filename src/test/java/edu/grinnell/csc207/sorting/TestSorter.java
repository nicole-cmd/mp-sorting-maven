package edu.grinnell.csc207.sorting;

import edu.grinnell.csc207.util.ArrayUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * Tests of Sorter objects. Please do not use this class directly.
 * Rather, you should subclass it and initialize stringSorter and
 * intSorter in a static @BeforeAll method.
 *
 * @author Nicole Gorrell
 * @uathor Samuel A. Rebelsky
 */
public class TestSorter {

  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  /**
   * The sorter we use to sort arrays of strings.
   */
  static Sorter<String> stringSorter = null;

  /**
   * The sorter we use to sort arrays of integers.
   */
  static Sorter<Integer> intSorter = null;

  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Given a sorted array and a permutation of the array, sort the
   * permutation and assert that it equals the original.
   *
   * @param <T>
   *   The type of values in the array.
   * @param sorted
   *   The sorted array.
   * @param perm
   *   The permuted sorted array.
   * @param sorter
   *   The thing to use to sort.
   */
  public <T> void assertSorts(T[] sorted, T[] perm, Sorter<? super T> sorter) {
    T[] tmp = perm.clone();
    sorter.sort(perm);
    assertArrayEquals(sorted, perm,
      () -> String.format("sort(%s) yields %s rather than %s",
          Arrays.toString(tmp), 
          Arrays.toString(perm), 
          Arrays.toString(sorted)));
  } // assertSorts

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * A fake test. I've forgotten why I've included this here. Probably
   * just to make sure that some test succeeds.
   */
  @Test
  public void fakeTest() {
    assertTrue(true);
  } // fakeTest()

  /**
   * Ensure that an array that is already in order gets sorted correctly.
   */
  @Test
  public void orderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    String[] expected = original.clone();
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that an array that is ordered backwards gets sorted correctly.
   */
  @Test
  public void reverseOrderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "foxtrot", "delta", "charlie", "bravo", "alpha" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that a randomly permuted version of a moderate-sized
   * array sorts correctly.
   */
  @Test 
  public void permutedIntegersTest() { 
    int SIZE = 100; 
    if (null == intSorter) { 
      return; 
    } // if
    Integer[] original = new Integer[SIZE];
    for (int i = 0; i < SIZE; i++) {
      original[i] = i;
    } // for
    Integer[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, intSorter);
  } // permutedIntegers

  /**
   * Ensure that an empty array remains as such in its
   * sorted state.
   */
  @Test
  public void emptyArrayTest() {
    if (null == intSorter) {
      return;
    } // if

    Integer[] original = { };
    Integer[] expected = original.clone();
    assertSorts(expected, original, intSorter);
  } // emptyArrayTest()

  /**
   * Ensure that a singleton array remains as such in
   * its sorted state.
   */
  @Test
  public void singletonTest() {
    if (null == intSorter) {
      return;
    } // if

    Integer[] original = { 1 };
    Integer[] expected = original.clone();
    assertSorts(expected, original, intSorter);
  } // singletonTest()

  /**
   * Ensure that two two separate arrays are sorted
   * correctly. Then combine the arrays and check
   * that the final combined array is correctly
   * sorted.
   */
  @Test
  public void sortMergeTest() {
    if (null == stringSorter) {
      return;
    } // if

    String[] o1 = { "i", "like", "animals" };
    String[] e1 = { "animals", "i", "like" };
    assertSorts(e1, o1, stringSorter);

    String[] o2 = { "joey", "is", "allergic", "to", "fur" };
    String[] e2 = { "allergic", "fur", "is", "joey", "to" };
    assertSorts(e2, o2, stringSorter);

    String[] originalMerge = new String[o1.length + o2.length];

    // merging our two arrays
    for (int i = 0; i < originalMerge.length; i++) {
      if (i < o1.length) {
        originalMerge[i] = o1[i];
      } // if

      originalMerge[i] = o2[i];
    } // for

    String[] expectedMerge = { "allergic", "animals", "fur", "i", "is", 
                              "like", "joey", "to" };
    assertSorts(expectedMerge, originalMerge, stringSorter);
  } // sortMergeTest()

  /**
   * Split an array of integers into two, sort both,
   * and verify that each are sorted correctly.
   */
  public void splitSortTest() {
    if (null == intSorter) {
      return;
    } // if

    int max = 50; // the maximum length of our original array
    Integer[] originalLong = new Integer[max];
    
    // our expected sorts we will compare to
    Integer[] firstSorted = new Integer[max / 2];
    Integer[] secondSorted = firstSorted.clone();

    for (int i = 0; i < max; i++) {
      originalLong[i] = i;

      if (i < max / 2) {
        firstSorted[i] = i;
      } // if

      secondSorted[i] = i;
    } // for

    ArrayUtils.permute(originalLong);

    Integer[] halfOne = new Integer[originalLong.length / 2];
    Integer[] halfTwo = halfOne.clone();

    for (int i = 0; i < originalLong.length; i++) {
      if (i < (originalLong.length / 2)) {
        halfOne[i] = originalLong[i];
      } // if

      halfTwo[i] = originalLong[i];
    } // for

    assertSorts(firstSorted, halfOne, intSorter);
    assertSorts(secondSorted, halfTwo, intSorter);
  } // splitSortTest()

  /**
   * Check that an array of randomly permuted strings
   * sorts correctly.
   */
  @Test
  public void permutedStringsTest() {
    if (null == stringSorter) {
      return;
    } // if

    String[] original = { "you", "have", "a", "message", "to", "read" };
    String[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, stringSorter);
  } // permutedStringsTest()

} // class TestSorter
