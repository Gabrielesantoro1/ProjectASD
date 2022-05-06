#define UNITY_INCLUDE_DOUBLE

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "unity.h"
#include "array.h"
#include "b_insertionsort.h"
#include "quicksort.h"

typedef struct record{
    char* string_field;
    int integer_field;
    float float_field;
} record;

static int precedes_record_int_field(void* r1_p, void* r2_p){
    struct record *rec1_p = (struct record*)r1_p;
    struct record *rec2_p = (struct record*)r2_p;
    if(rec1_p->integer_field == rec2_p->integer_field){
        if(strcmp(rec1_p->string_field,rec2_p->string_field) < 0){
            return(1);
        }
    }else if(rec1_p->integer_field < rec2_p->integer_field){
        return(1);
        }
    return(0);
}

static int precedes_record_string_field(void* r1_p,void* r2_p){
    struct record *rec1_p = (struct record*)r1_p;
    struct record *rec2_p = (struct record*)r2_p;
    if(strcmp(rec1_p->string_field,rec2_p->string_field)==0){
        if(rec1_p->integer_field < rec2_p->integer_field){
            return(1);
        }
    }else if(strcmp(rec1_p->string_field,rec2_p->string_field)<0){
        return(1);
    }
    return(0);
}
static int precedes_record_float_field(void* r1_p, void* r2_p){
    struct record *rec1_p = (struct record*)r1_p;
    struct record *rec2_p = (struct record*)r2_p;
    if(rec1_p->float_field == rec2_p->float_field){
        if(rec1_p->integer_field < rec2_p->integer_field){
            return(1);
        }
    }else if(rec1_p->float_field < rec2_p->float_field){
        return(1);
    }
    return(0);
}

struct record r1, r2, r3;
static int i1,i2,i3;
static Array_Struct *array_struct;

void setUp(void){
    i1 = -12;
    i2 = 0;
    i3 = 4;
    array_struct = array_create();
    r1.integer_field = 3;
    r2.integer_field = 2;
    r3.integer_field = 1;
    
    r1.string_field = "c";
    r2.string_field = "b";
    r3.string_field = "a";

    r1.float_field = 3.5;
    r2.float_field = 2.5;
    r3.float_field = 1.5;

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

static void test_insertsort_with_random_array_int(void){   
    array_struct_add(array_struct, &r1);
    array_struct_add(array_struct, &r2);
    array_struct_add(array_struct, &r3);
    b_insertionsort(array_struct, precedes_record_int_field);
    TEST_ASSERT_EQUAL_INT_ARRAY((1,2,3),array_struct->array, 3);
}

static void test_insertsort_with_random_array_float(void){
    array_struct_add(array_struct, &r1);
    array_struct_add(array_struct, &r2);
    array_struct_add(array_struct, &r3);
    b_insertionsort(array_struct, precedes_record_float_field);
    //TEST_ASSERT_EQUAL_DOUBLE_ARRAY((1.5, 2.5, 3.5), (double **)array_struct->array, 3);
}

static void test_insertsort_with_random_array_string(void){
    array_struct_add(array_struct, &r1);
    array_struct_add(array_struct, &r2);
    array_struct_add(array_struct, &r3);
    b_insertionsort(array_struct, precedes_record_string_field);
    TEST_ASSERT_EQUAL_STRING_ARRAY(("a","b", "c"),array_struct->array, 3);
}

static void test_quicksort_with_random_array_int(void){
    array_struct_add(array_struct, &r1);
    array_struct_add(array_struct, &r2);
    array_struct_add(array_struct, &r3);
    quick_sort(array_struct, precedes_record_int_field, 0, 2, 0);
    TEST_ASSERT_EQUAL_INT_ARRAY((1,2,3), array_struct->array, 3);
}

static void test_quicksort_with_random_array_float(void){
    array_struct_add(array_struct, &r1);
    array_struct_add(array_struct, &r2);
    array_struct_add(array_struct, &r3);
    quick_sort(array_struct, precedes_record_float_field, 0, 2, 0);
    //TEST_ASSERT_EQUAL_DOUBLE_ARRAY((1.5, 2.5, 3.5), (double *)array_struct->array, 3);
}

static void test_quicksort_with_random_array_string(void){
    array_struct_add(array_struct, &r1);
    array_struct_add(array_struct, &r2);
    array_struct_add(array_struct, &r3);
    quick_sort(array_struct, precedes_record_string_field, 0, 2, 0);
    TEST_ASSERT_EQUAL_STRING_ARRAY(("a", "b", "c"), array_struct->array, 3);
}

int main()
{
UNITY_BEGIN();

RUN_TEST(test_array_struct_is_empty_one_el);
RUN_TEST(test_array_struct_is_empty_zero_el);
RUN_TEST(test_array_struct_size_one_el);
RUN_TEST(test_array_struct_size_zero_el);
RUN_TEST(test_array_struct_add_get_el);

//RUN_TEST(test_insertsort_with_random_array_int);
//RUN_TEST(test_quicksort_with_random_array_int);
//RUN_TEST(test_insertsort_with_random_array_float);
//RUN_TEST(test_quicksort_with_random_array_float);
//RUN_TEST(test_quicksort_with_random_array_string);
//RUN_TEST(test_insertsort_with_random_array_string);

return UNITY_END();
}
