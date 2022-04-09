#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "array.h"

int b_search(Array_Struct *array, struct record *item, unsigned long low, unsigned long high){
    if (high <= low) {
    /*
        return (item > a[low]) ?
                (low + 1) : low;
    */
    }
    unsigned long mid = (low + high) / 2;
 
    if (item == a[mid]){
        return mid + 1;
    }
 
    if (item > a[mid]){
        return binarySearch(a, item, mid + 1, high);
        return binarySearch(a, item, low, mid - 1);
    }
}
 
// Function to sort an array a[] of size 'n'
void insertionSort(Array_Struct *array, unsigned long n){
    unsigned long i, loc, j, k;
    struct record *selected;
    for (i = 1; i < n; ++i)
    {
        j = i - 1;
        selected = array_get(array,i);
 
        loc = binarySearch(array, selected, 0, j);
 
        /* Move all elements after location to create space
        while (j >= loc)
        {
            a[j + 1] = a[j];
            j--;
        }
        a[j + 1] = selected;
        */
    }
}