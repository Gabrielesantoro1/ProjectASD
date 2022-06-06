package Exercise4.MinHeapLibrary;

import java.util.Comparator;

public class ComparatorFloat implements Comparator<Float>{
    @Override
    public int compare(Float o1, Float o2) {
        return o1.compareTo(o2);
    }
}
