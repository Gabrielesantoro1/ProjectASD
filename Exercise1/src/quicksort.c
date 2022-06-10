#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "array.h"

/**
 * @brief Partition function for the pivot chosen as the last element
 * 
 * @param array_struct array to sort
 * @param precedes comparasion criterion
 * @param first first index of range
 * @param last last index of range
 * @return int 
 */
int LastPartition(Array_Struct *array_struct, int (*precedes)(void*,void*), long first, long last){
    void* pivot = array_struct->array[last];
    long i = first - 1;
    for(int j = first; j < last; j++){
        if((precedes(array_struct->array[j], pivot))==1){
            i++;
            void* tmp = array_struct->array[j];
            array_struct->array[j] = array_struct->array[i];
            array_struct->array[i] = tmp;
        }
    }
    void* tmp = array_struct->array[last];
    array_struct->array[last] = array_struct->array[i+1];
    array_struct->array[i+1] = tmp;
    return i+1;
}

/**
 * @brief Partition function for the pivot chosen as the first element
 * 
 * @param array_struct array to sort
 * @param precedes comparasion criterion
 * @param first first index of range
 * @param last last index of range
 * @return int 
 */
int FirstPartition(Array_Struct *array_struct, int (*precedes)(void*,void*), long first, long last){
    void* pivot = array_struct->array[first];
    long i = last + 1;
    for(int j = last; j > first; j--){
        if((precedes(array_struct->array[j], pivot))==0){
            i--;
            void* tmp = array_struct->array[j];
            array_struct->array[j] = array_struct->array[i];
            array_struct->array[i] = tmp;
        }
    }
    void* tmp = array_struct->array[first];
    array_struct->array[first] = array_struct->array[i-1];
    array_struct->array[i-1] = tmp;
    return i-1;
}

/**
 * @brief Partition function for the pivot chosen as random element
 * 
 * @param array_struct array to sort
 * @param precedes comparasion criterion
 * @param first first index of range
 * @param last last index of range
 * @return int 
 */
int RandomizedPartition(Array_Struct *array_struct, int (*precedes)(void*,void*), long first, long last){
    long pivot = (rand()%((last-first+1))+first);
    void *tmp = array_struct->array[last];
    array_struct->array[last] = array_struct->array[pivot];
    array_struct->array[pivot] = tmp;
    return LastPartition(array_struct,precedes,first,last);
}

/**
 * @brief quicksort function
 * 
 * @param array_struct array to sort
 * @param precedes comparasion criterion
 * @param p fisrt index range of array
 * @param r last index range of array
 * @param crit pivot chosen (0:first - 1:last - 2:random)
 */
void quick_sort_algo(Array_Struct *array_struct, int (*precedes)(void*,void*), long p, long r, long crit){
    long q;
    srand(time(NULL));
    if(p<r){
        switch (crit)
        {
        case 0:
            q = FirstPartition(array_struct, precedes, p, r);
            //printf("First %d - Last %d - Q %d\n",p,r,q);
            quick_sort_algo(array_struct, precedes, p, q-1, crit);
            quick_sort_algo(array_struct, precedes, q+1, r, crit);
            break;
        case 1:
            q = LastPartition(array_struct, precedes, p, r);
            //printf("First %d - Last %d - Q %d\n",p,r,q);
            quick_sort_algo(array_struct, precedes, p, q-1, crit);
            quick_sort_algo(array_struct, precedes, q+1, r, crit);
            break;
        case 2:
            q = RandomizedPartition(array_struct, precedes, p, r);
            //printf("First %d - Last %d - Q %d\n",p,r,q);
            quick_sort_algo(array_struct, precedes, p, q-1, crit);
            quick_sort_algo(array_struct, precedes, q+1, r, crit);
            break;
        default:
            fprintf(stderr,"ERRORE CRIT");
            break;
        }   
    }
}