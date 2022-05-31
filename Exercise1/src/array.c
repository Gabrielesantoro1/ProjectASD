#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "array.h"

#define INITIAL_CAPACITY 10

/**
 * @brief The function returns an integer value, interpreted as a Boolean indicating whether the array is empty or not
 * 
 * @param array_struct : array where to check
 * @return int : 0(false) if array is not empty, 1(true) otherwise
 */
int array_is_empty(Array_Struct *array_struct){
  if(array_struct == NULL){
    fprintf(stderr,"array_is_empty: array_struct parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }
    if(array_struct->el_num == 0){
      return(1);
    }else{
      return(0);
    }
}

/**
 * @brief creates the structure of the array, allocating the necessary memory and initializing the values ​​inside
 * 
 * @return Array_Struct* 
 */
Array_Struct *array_create(){
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
  array_struct->el_num = 0;
  array_struct->array_capacity = INITIAL_CAPACITY;
  return array_struct;
}

/**
 * @brief frees the memory dedicated to the array
 * 
 * @param array_struct 
 */
void array_free_memory(Array_Struct *array_struct){
   if(array_struct == NULL){
    fprintf(stderr, "The array cannot be null, array_free_memory");
    exit(EXIT_FAILURE);
  }else{
    free(array_struct->array);
    free(array_struct);
  }
}

/**
 * @brief calculates the size of the array
 * 
 * @param array_struct 
 * @return unsigned long size of the array 
 */
unsigned long array_size(Array_Struct *array_struct){
  if(array_struct == NULL){
    fprintf(stderr, "The array cannot be null, array_size");
    exit(EXIT_FAILURE);
  }else{
    return array_struct->el_num;
  }
}

/**
 * @brief adds an element to the array, reallocates memory if necessary, and increments the structure values
 * 
 * @param array_struct 
 * @param element item to add
 */
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
    (array_struct->array)[array_struct->el_num] = element;
    (array_struct->el_num)++; 
}

/**
 * @brief returns if it exists, the element in the indicated position
 * 
 * @param array_struct 
 * @param i index of the desired element
 * @return void* 
 */
void* array_get(Array_Struct *array_struct, unsigned long i){ 
  if(array_struct == NULL){
      fprintf(stderr,"array_get: array_struct cannot be NULL");
      exit(EXIT_FAILURE);
    }
    if(i>=array_struct->el_num){
      fprintf(stderr,"array_get: Index out of the array bounds",i);
      exit(EXIT_FAILURE);
    }
    return(array_struct->array)[i];
}

