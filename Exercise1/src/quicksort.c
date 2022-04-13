#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "array.h"

int Partition(Array_Struct *array_struct, int (*precedes)(void*,void*), long first, long last,long p){
    void* pivot = array_struct->array[p];
    long i = first + 1;
    long j = last;
    while(i<=j){
        if((precedes(array_struct->array[i], pivot))==1){
            i++;
        }
        else if((precedes(array_struct->array[j], pivot))==0){
            j--;
        }else {
            void *temp=array_struct->array[i];
            array_struct->array[i]=array_struct->array[j];
            array_struct->array[j]=temp;
            i++;
            j--;
        }
    }
    long q = i - 1;
    void *tmp = array_struct->array[first];
    array_struct->array[first]=array_struct->array[q];
    array_struct->array[q]=tmp;
    return q;
}

void quick_sort(Array_Struct *array_struct, int (*precedes)(void*,void*), long p, long r, long crit, long pivot){
    long q;
    if(p<r){
        switch (crit)
        {
        case 0:
            q = Partition(array_struct, precedes, p, r, p);
            quick_sort(array_struct, precedes, p, q-1, crit ,p);
            quick_sort(array_struct, precedes, q+1, r, crit, q+1);
            break;
        case 1:
            q = Partition(array_struct, precedes, p, r, r);
            quick_sort(array_struct, precedes, p, q-1, crit ,q-1);
            quick_sort(array_struct, precedes, q+1, r, crit, r);
            break;
        case 2:
            q = Partition(array_struct, precedes, p, r, (rand()%r)+1);
            quick_sort(array_struct, precedes, p, q-1, crit ,(rand()%(q-1))+1);
            quick_sort(array_struct, precedes, q+1, r, crit, (rand()%r)+1);
            break;
        default:
            fprintf(stderr,"ERRORE CRIT");
            break;
        }
        
    }
}