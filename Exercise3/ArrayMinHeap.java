package Exercise3;

import java.util.ArrayList;
import java.util.Comparator;

public class ArrayMinHeap<T> extends ArrayList<T> implements Comparator<T>{

    @Override
    public int compare(Object o1, Object o2) {
        if((int)o1 == (int)o2){
            return 0;
        }else if((int)o1 < (int)o2){
            return -1;
        }else{
            return 1;
        }
    }
    
}
