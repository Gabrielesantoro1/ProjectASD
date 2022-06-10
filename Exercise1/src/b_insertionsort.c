#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "array.h"

/**
 * @brief binary search function
 * 
 * @param array_struct 
 * @param item item to search
 * @param low lowest range
 * @param max higher range
 * @param precedes comparison criterion
 * @return long position of the element
 */
long b_search(Array_Struct *array_struct, void *item, long low, long max, int (*precedes)(void*, void*)){        
    if(low == max){
        if((precedes(item, array_struct->array[low])==1)){
            return low;
        }else{
            return max+1;
        }
    }else{
        long mid = (low + max)/2;
        if((precedes(item, array_struct->array[mid])==1)){
            b_search(array_struct, item, low, mid, precedes);
        }else if((precedes(item, array_struct->array[mid]))==0){
            b_search(array_struct, item, mid+1, max, precedes);
        }
    }
    return(EXIT_FAILURE);
}
 
/**
 * @brief insertion sort function
 * 
 * @param array_struct array to sort
 * @param precedes comparison criterion
 * @return Array_Struct* ordered array
 */
Array_Struct* b_insertionsort(Array_Struct *array_struct, int (*precedes)(void*, void*)){
    long i, loc, k;
    long lenght = array_size(array_struct);
    void *selected;

    for (i = 1; i < lenght; i++){
        selected = (array_struct->array)[i] ;
        loc = b_search(array_struct, selected, 0, i-1, precedes);
        k = i-1;
        //Move all elements after location to create space
        while (k>=loc){
            (array_struct->array)[k+1] = (array_struct->array)[k];
            k--;
        }
        (array_struct->array)[k + 1] = selected;
    }
    return array_struct;
}
