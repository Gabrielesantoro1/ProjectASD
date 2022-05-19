package Exercise3;

import java.util.Comparator;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


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
public class MinHeapTests {

    class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
          return i1.compareTo(i2);
        }
    }


    private Integer i1,i2,i3;
    private Float f1, f2, f3;
    private String s1, s2, s3;
    private MinHeap<Integer> heapArray;

    @Before
    public void createMinHeap() throws Exception{
        i1=1;
        i2=2;
        i3=3;
        heapArray = new MinHeap<>(new IntegerComparator());
    }

    @Test
    public void testIsEmpty_zeroEl(){
        assertEquals(0,heapArray.getHeapSize());
    }

    @Test
    public void testadd_oneEl(){
        heapArray.minHeapInsert(i1);
        assertEquals(i1, heapArray.getHeapSize());
    }

    @Test
    public void test_giveMin(){
        heapArray.minHeapInsert(i1);
        heapArray.minHeapInsert(i2);
        heapArray.minHeapInsert(i3);
        assertEquals(i1, heapArray.heapExtractMin());
    }
        
}
