#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "array.h"

struct Array_struct{
  void** array;
  unsigned long el_num;
  unsigned long array_capacity;
  int (*precedes)(void*,void*);
};

INITIAL_CAPACITY 5

int array_is_empty(Array_Struct *array_struct){
  if(array_struct == NULL)
    fprintf(stderr,"array_is_empty: array_struct parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }
  if(array_struct->el_num == 0)
    return(1);
  return(0);
}

Array_Struct *array_create(void){
  Array_Struct *array_struct = (Array_Struct *)malloc(sizeof(Array_Struct));
  if(array_struct == NULL){
    fprintf(stderr,"array_create: array_struct cannot be NULL");
    exit(EXIT_FAILURE);
  }
  array_struct->array = (void **)malloc(INITIAL_CAPACITY * sizeof(void *));
  if(array_struct->array == NULL){
    fprintf(stderr,"array_create: array cannot be NULL");
    exit(EXIT_FAILURE);
  }
  array_struct->el_num=0;
  array_struct->array_capacity = INITIAL_CAPACITY;

  return array_struct;
}

void array_free_memory(Array_Struct *array_struct){
   if(array_struct == NULL){
    fprintf(stderr, "The array cannot be null, array_free_memory");
    exit(EXIT_FAILURE);
  }else{
    free(array_struct->array);
    free(array_struct);
  }
}

int array_size(Array_Struct *array_struct){
  if(array_struct == NULL){
    fprintf(stderr, "The array cannot be null, array_size");
    exit(EXIT_FAILURE);
  }else
    return array_struct->el_num;
}


void array_struct_add(Array_Struct *array_struct, void* element){
  if(array_struct == NULL){
    fprintf(stderr,"add_ordered_array_element: ordered_array parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }
  if(element == NULL){
    fprintf(stderr,"add_ordered_array_element: element parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }
  
  if(array_struct->el_num >= array_struct->array_capacity){
    array_struct->array = (void**)realloc(array_struct->array,2*(array_struct->array_capacity)*sizeof(void*));
    if(array_struct->array == NULL){
      fprintf(stderr,"array_struct_add: unable to reallocate memory to host the new element");
      exit(EXIT_FAILURE);
    }
    array_struct->array_capacity = 2*array_struct->array_capacity;
  }  
  (array_struct->el_num)++;

  (array_struct->array)[array_struct->el_num] = element;
  
}

