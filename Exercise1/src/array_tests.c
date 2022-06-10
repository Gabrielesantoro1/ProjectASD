#define UNITY_INCLUDE_DOUBLE

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "unity.h"
#include "array.h"
#include "b_insertionsort.h"
#include "quicksort.h"

static int precedes_int(void* r1_p, void* r2_p){
    if((int*)r1_p == (int*)r2_p){
        return 1;
    }else if((int*)r1_p < (int*)r2_p){
        return 1;
    }
    return 0;
}

static int precedes_string(void* r1_p,void* r2_p){
    if(strcmp((char*)r1_p,(char*)r2_p)==0){
        return 1;
    }else if(strcmp((char*)r1_p,(char*)r2_p)<0){
        return 1;
    }
    return 0;
}

static int precedes_float(void* r1_p, void* r2_p){
    if((float*)r1_p == (float*)r2_p){
        return 1;
    }else if((float*)r1_p < (float*)r2_p){
        return 1;
    }
    return 0;
}

static int i1,i2,i3;
static float f1,f2,f3;
static char s1,s2,s3;
static Array_Struct *array_struct;

void setUp(void){
    i1 = -12;
    i2 = 0;
    i3 = 4;
    
    f1 = 2.0;
    f2 = 3.0;
    f3 = 5.0;

    s1 = 'a';
    s2 = 'b';
    s3 = 'c';
    
    array_struct = array_create();
}

void tearDown(void){
    array_free_memory(array_struct);
}


static void test_array_struct_is_empty_zero_el(void){
    TEST_ASSERT_TRUE(array_is_empty(array_struct));
}


static void test_array_struct_is_empty_one_el(void){
    array_struct_add(array_struct,(void*)i1);
    TEST_ASSERT_FALSE(array_is_empty(array_struct));
}


static void test_array_struct_size_zero_el(void){
    TEST_ASSERT_EQUAL_INT(0,array_size(array_struct));
}


static void test_array_struct_size_one_el(void){
    array_struct_add(array_struct,(void*)i1);
    TEST_ASSERT_EQUAL_INT(1,array_size(array_struct));
}


static void test_array_struct_add_get_el(void){
  int* exp_arr[] = {&i1,&i2,&i3};
  array_struct_add(array_struct,&i1);
  array_struct_add(array_struct,&i2);
  array_struct_add(array_struct,&i3);
  int** act_arr = malloc(3*sizeof(int*));
  for(unsigned long i=0;i<3;i++)
    act_arr[i] = (int*)array_get(array_struct,i);
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,3);
}


static void test_insertsort_with_array_int(void){
  int* exp_arr[] = {&i1,&i2,&i3};
  array_struct_add(array_struct,&i2);
  array_struct_add(array_struct,&i1);
  array_struct_add(array_struct,&i3);
  array_struct = b_insertionsort(array_struct,precedes_int);
  int** act_arr = malloc(3*sizeof(int*));
  for(unsigned long i=0;i<3;i++)
    act_arr[i] = (int*)array_get(array_struct,i);
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,3);
}


static void test_insertsort_with_array_float(void){
  float* exp_arr[] = {&f1,&f2,&f3};
  array_struct_add(array_struct,&f1);
  array_struct_add(array_struct,&f3);
  array_struct_add(array_struct,&f2);
  array_struct = b_insertionsort(array_struct,precedes_float);
  float** act_arr = malloc(3*sizeof(float*));
  for(unsigned long i=0;i<3;i++)
    act_arr[i] = (float*)array_get(array_struct,i);
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,3);
}

//Da rivedere
static void test_insertsort_with_array_string(void){
  char* exp_arr[] = {&s1,&s2,&s3};
  array_struct_add(array_struct,&s2);
  array_struct_add(array_struct,&s1);
  array_struct_add(array_struct,&s3);
  array_struct = b_insertionsort(array_struct,precedes_string);
  char** act_arr = malloc(3*sizeof(char*));
  for(unsigned long i=0;i<3;i++)
    act_arr[i] = (char*)array_get(array_struct,i);
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,3);
}

static void test_quicksort_with_array_int(void){
  int* exp_arr[] = {&i1,&i2,&i3};
  array_struct_add(array_struct,&i2);
  array_struct_add(array_struct,&i1);
  array_struct_add(array_struct,&i3);
  quick_sort_algo(array_struct,precedes_int,0,2,0);
  int** act_arr = malloc(3*sizeof(int*));
  for(unsigned long i=0;i<3;i++)
    act_arr[i] = (int*)array_get(array_struct,i);
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,3);
}

static void test_quicksort_with_array_float(void){
  float* exp_arr[] = {&f1,&f2,&f3};
  array_struct_add(array_struct,&f1);
  array_struct_add(array_struct,&f3);
  array_struct_add(array_struct,&f2);
  quick_sort_algo(array_struct,precedes_float,0,2,0);
  float** act_arr = malloc(3*sizeof(float*));
  for(unsigned long i=0;i<3;i++)
    act_arr[i] = (float*)array_get(array_struct,i);
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,3);
}

//Da rivedere
static void test_quicksort_with_array_string(void){
  char* exp_arr[] = {&s1,&s2,&s3};
  array_struct_add(array_struct,&s1);
  array_struct_add(array_struct,&s2);
  array_struct_add(array_struct,&s3);
  quick_sort_algo(array_struct,precedes_string,0,2,0);
  char** act_arr = malloc(3*sizeof(char*));
  for(unsigned long i=0;i<3;i++)
    act_arr[i] = (char*)array_get(array_struct,i);
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,3);
}
 

int main()
{
UNITY_BEGIN();

RUN_TEST(test_array_struct_is_empty_one_el);
RUN_TEST(test_array_struct_is_empty_zero_el);
RUN_TEST(test_array_struct_size_one_el);
RUN_TEST(test_array_struct_size_zero_el);
RUN_TEST(test_array_struct_add_get_el);

//Test for InsertionSort
RUN_TEST(test_insertsort_with_array_int);
RUN_TEST(test_insertsort_with_array_float);
RUN_TEST(test_insertsort_with_array_string);

//Test for QuickSort
RUN_TEST(test_quicksort_with_array_int);
RUN_TEST(test_quicksort_with_array_float);
RUN_TEST(test_quicksort_with_array_string);

UNITY_END();
}
