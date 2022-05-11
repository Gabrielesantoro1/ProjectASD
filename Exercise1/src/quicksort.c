#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "array.h"

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

int RandomizedPartition(Array_Struct *array_struct, int (*precedes)(void*,void*), long first, long last){
    long pivot = (rand()%((last-first+1))+first);
    void *tmp = array_struct->array[last];
    array_struct->array[last] = array_struct->array[pivot];
    array_struct->array[pivot] = tmp;
    return LastPartition(array_struct,precedes,first,last);
}

void quick_sort(Array_Struct *array_struct, int (*precedes)(void*,void*), long p, long r, long crit){
    long q;
    srand(time(NULL));
    if(p<r){
        switch (crit)
        {
        case 0:
            q = FirstPartition(array_struct, precedes, p, r);
            //printf("First %d - Last %d - Q %d\n",p,r,q);
            quick_sort(array_struct, precedes, p, q-1, crit);
            quick_sort(array_struct, precedes, q+1, r, crit);
            break;
        case 1:
            q = LastPartition(array_struct, precedes, p, r);
            //printf("First %d - Last %d - Q %d\n",p,r,q);
            quick_sort(array_struct, precedes, p, q-1, crit);
            quick_sort(array_struct, precedes, q+1, r, crit);
            break;
        case 2:
            q = RandomizedPartition(array_struct, precedes, p, r);
            //printf("First %d - Last %d - Q %d\n",p,r,q);
            quick_sort(array_struct, precedes, p, q-1, crit);
            quick_sort(array_struct, precedes, q+1, r, crit);
            break;
        default:
            fprintf(stderr,"ERRORE CRIT");
            break;
        }   
    }
}