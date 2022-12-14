package minheap;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;

/**
 * To use the test class:
 * 1)Compile the class where we have our methods to test:
 * javac nomeClasse.java
 * 
 * 2)Compile the test:
 * javac -cp .;junit-4.XX.jar;hamcrest-core-1.3.jar MinHeapTests.java
 *  
 * 3)Run the test:
 * java -cp .;junit-4.XX.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore MinHeapTests.java
 */
public class MinHeapTest {

    private Integer i1,i2,i3,i4;
    private String s1, s2, s3, s4;
    private MinHeap<Integer> heapArrayInt;
    private MinHeap<String> heapArrayStr;

    @Before
    public void createMinHeap() throws Exception{
        i1=1;
        i2=2;
        i3=3;
        i4=0;
        heapArrayInt = new MinHeap<>(new ComparatorInteger());
        
        s1="Hello";
        s2="Good";
        s3="abc";
        s4="a";
        heapArrayStr = new MinHeap<>(new ComparatorString());
    }

    @Test
    public void testIsEmptyZeroEl(){
        assertEquals(0,heapArrayInt.getHeapSize());
    }

    @Test
    public void testSizeOneEl(){
        try {
          heapArrayInt.minHeapInsert(i1);
        } catch (MinHeapException e) {
          e.printStackTrace();
        }
        assertEquals(1, heapArrayInt.getHeapSize());
    }

    @Test
    public void testExtractMin(){
        try {
          heapArrayInt.minHeapInsert(i1);
          heapArrayInt.minHeapInsert(i2);
          heapArrayInt.minHeapInsert(i3);
          assertEquals(i1, heapArrayInt.heapExtractMin());
        } catch (MinHeapException e) {
          e.printStackTrace();
        }
    }
    
    @Test
    public void testDecreaseKey() {
        try {
          heapArrayInt.minHeapInsert(i2);
          heapArrayInt.heapDecreaseKey(0, 0);
          assertEquals(i4, heapArrayInt.heapExtractMin());
        } catch (MinHeapException e) {
          e.printStackTrace();
        }
    }

    @Test
    public void testAddOneEl_str(){
        try {
          heapArrayStr.minHeapInsert(s1);
          assertEquals(s1, heapArrayStr.getArray().get(0));
        } catch (MinHeapException e) {
          e.printStackTrace();
        }    
    }

    @Test
    public void testExtractMinThree_str(){
        try {
          heapArrayStr.minHeapInsert(s1);
          heapArrayStr.minHeapInsert(s2);
          heapArrayStr.minHeapInsert(s3);
          assertEquals(s2, heapArrayStr.heapExtractMin());
        } catch (MinHeapException e) {
          e.printStackTrace();
        }
    }

    @Test
    public void testExtractMinOne_str(){
        try {
          heapArrayStr.minHeapInsert(s1);
          assertEquals(s1, heapArrayStr.heapExtractMin());
        } catch (MinHeapException e) {
          e.printStackTrace();
        }
    }
    
    @Test
    public void testDecreaseKey_str() {
        try {
          heapArrayStr.minHeapInsert(s3);
          heapArrayStr.heapDecreaseKey(0, "a");
          assertEquals(s4, heapArrayStr.heapExtractMin());
        } catch (MinHeapException e) {
          e.printStackTrace();
        }
    }
    
}
