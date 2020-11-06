
/////////////////////////////// EXAM FILE HEADER ///////////////////////////////
// Full Name: Frank Slavinsky
// Campus ID: 9080811392
// WiscEmail: fslavinsky@wisc.edu
////////////////////////////////////////////////////////////////////////////////      

import java.util.Arrays;

/**
 *  This class contains a single public method: sortHeapOfBooks(), along with 
 *  several helper methods and a test.  This public method uses the heap sort
 *  algorithm to sort book titles in lexicographic order, but skips the initial
 *  heapify step, since the books are provided in min-heap order.
 *  
 *  // TODO #1-6 below ask you to finish implementing the following methods
 *  (only make edits below each of these numbered TODOs, as described for each):
 *  peekBook() - returns earliest book title from the root position of the min-heap
 *  percolateDown() - recursively enforces heap ordering down from specified index
 *  removeBook() - removes book from root position in heap, and returns its new size
 *  sortHeapOfBooks() - computes and returns a sorted array of book titles
 *  
 *  Additionally, the following helper and test methods are provided:
 *  swap() - swaps the values at two different positions within an array
 *  bestOf() - returns the earliest of its three string/null arguments
 *  test() - returns true when this test passes and false otherwise
 *  main() - calls and prints out the value returned from test()
 */
public class BookSorter {    

  /**
   * Returns book title from the root position in the min-heap (lexicographically earliest title) 
   * @param heap a provided min-heap
   * @return book title from the root position in the min-heap 
   */
  private static String peekBook(String[] heap) {
    return heap[0];
  }
    
  /**
   * Recursive helper method which implements percolateDown operation to restore the
   * heap order property if it is violated at a given index position of a provided min-heap.
   * 
   * @param index position of the element to start the percolate-down operation from
   * @param heap a provided min-heap array
   * @param size of the heap
   */
  private static void percolateDown(int index, String[] heap, int size) {
    if(index >= size) return; // cannot percolate further below bottom of heap
    // compute indexes of left and right children positions within heap
    int leftChildIndex = 2 * index + 1;
    int rightChildIndex = 2 * index + 2;

    // store values from index, left, and right child positions into separate variables
    String indexValue = heap[index];
    String rightChildValueOrNull = null;
    if(rightChildIndex<size) rightChildValueOrNull = heap[rightChildIndex];
    String leftChildValueOrNull = null;
    if(leftChildIndex<size) leftChildValueOrNull = heap[leftChildIndex];        
    // compute highest priority value between index and its left and right children
    String best = bestOf(indexValue, rightChildValueOrNull, leftChildValueOrNull);
    
    if( best.equals(leftChildValueOrNull) ) {
      // TODO #2: implement percolate down through left subtree
      percolateDown(leftChildIndex, heap, size--);
      
    } else if( best.equals(rightChildValueOrNull) ) {
      // TODO #3: implement percolate down through right subtree
      percolateDown(rightChildIndex, heap, size--);
      
    }
  }

  /**
   * Removes book from root position in heap, and then returns heap's new size
   * @param heap a provided min-heap
   * @param size of heap before removeBook is called.
   * @return size is the new size of heap after removeBook is performed
   */
  private static int removeBook(String[] heap, int size) {
    percolateDown(1,heap, size);
    return size;
  }    

  /**
   * Sorts an array of book titles in the lexicographic order using a min-heap.
   * This sort operation does not operate in-place.
   * @param heap a min-heap
   * @return reference to the sorted array
   */
  public static String[] sortHeapOfBooks(String[] heap) {
    int heapSize = heap.length;
    // Sorted is an over sized array that will be filled with Books 
    // in sorted order.  When it is full, it will be returned.
    String[] sorted = new String[heap.length];
    int sortedSize = 0;
    
    while(heapSize > 0) {
      // TODO #5: insert best element from heap into earliest free position in sorted array
      sorted[sortedSize] = peekBook(heap);
      sortedSize++;
            
      // TODO #6: remove the best value from the heap
      removeBook(heap, heapSize--);
    }
    
    return sorted;
  }

  /**
   * One short and simple test for the sortHeapOfBooks method.
   * @return true when this test passes, otherwise false
   */
  private static boolean test() {
    String[] heapOfBooks = new String[] {
        "Abstract Data Types",
        "Big-O Analysis",
        "Algorithms",
        "OO Design",
        "Debugging",
        "DataStructures",
    };
    String[] sortedBooks = new String[] {
        "Abstract Data Types",
        "Algorithms",
        "Big-O Analysis",
        "DataStructures",
        "Debugging",
        "OO Design",
    };
    return Arrays.deepEquals(sortHeapOfBooks(heapOfBooks), sortedBooks);
  }
  
  /**
   * Driver for the test method above.
   * @param args is unused
   */
  public static void main(String[] args) {
    System.out.println("test(): "+test());
  }
  
  /**
   * Swaps the elements at indexes i and j in the provided array
   * @param array in which elements are being moved
   * @param i the first index being changed in that array
   * @param j the second index being changed in that array
   */
  private static void swap(String[] array, int i, int j) {
    String temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  /**
   * Returns the lexicographically earliest string between the provided arguments.
   * Null arguments are ignored (considered lexicographically later than strings).
   * @param a, b, c are the strings being compared
   * @return the lexicographically earliest of those strings
   * @throws IllegalArgumentException when all strings are null
   */
  private static String bestOf(String a, String b, String c) {
    if(a != null && 
        (b == null || a.compareTo(b) <= 0) && 
        (c == null || a.compareTo(c) <= 0))
        return a;
    else if(b != null && 
        (a == null || b.compareTo(a) <= 0) && 
        (c == null || b.compareTo(c) <= 0)) 
        return b;
    else if(c != null && 
        (a == null || c.compareTo(a) <= 0) && 
        (b == null || c.compareTo(b) <= 0))
      return c;
    else
      throw new IllegalArgumentException("None of these strings are the best");
  }

}