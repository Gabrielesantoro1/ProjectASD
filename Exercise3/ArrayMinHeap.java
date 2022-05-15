package Exercise3;

import java.util.ArrayList;
import java.util.Comparator;

public class ArrayMinHeap<T> extends ArrayList<T> implements Comparator<T>{

    @Override
    public int compare(Object o1, Object o2) {
        if(o1.getClass() == o2.getClass()){
            if(o1.getClass() == int.class){
                int i1 = (int)o1;
                int i2 = (int)o2;
                if(i1 == i2){
                    return 0;
                }else if(i1 < i2){
                    return -1;
                }else{
                    return 1;
                }
            }
        }
        return 0;
    }
    
}
