#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "array.h"
#include "array.c"

unsigned long b_search(Array_Struct *array_struct, void *item, unsigned long low, unsigned long high, int (*precedes)(void*, void*)){
    if (high <= low) {
        return (((precedes)(item, (array_struct->array)[low]))) ?
                (low + 1) : low;
    }
    unsigned long mid = (low + high) / 2;
 
    /*
    if (item == array[mid]){
        return mid + 1;
    }
    */
 
    if (((precedes)(item, (array_struct->array)[mid]))){
        return b_search(array_struct, item, mid + 1, high, precedes);
        return b_search(array_struct, item, low, mid - 1, precedes);
    }
}
 
// Function to sort an array a[] of size 'n'
Array_Struct* b_insertionsort(Array_Struct *array_struct, int (*precedes)(void*, void*)){
    unsigned long i, loc, j, k;

    unsigned long lenght = array_size(array_struct);
    void *selected;
    for (i = 1; i < lenght; ++i){
        j = i - 1;
        selected = (array_struct->array)[i] ;
 
        loc = b_search(array_struct, selected, 0, j, precedes);
 
        //Move all elements after location to create space
        while (j >= loc)
        {
            (array_struct->array)[j + 1] = (array_struct->array)[j];
            j--;
        }
        (array_struct->array)[j + 1] = selected;
    }
}