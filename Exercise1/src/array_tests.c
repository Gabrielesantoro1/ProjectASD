#include <stdio.h>
#include <stdlib.h>
#include "unity.h"
#include "array.h"


static int i1,i2,i3;
static Array_Struct *array_struct;

void setUp(void){
    i1 = -12;
    i2 = 0;
    i3 = 4;
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


int main()
{
UNITY_BEGIN();

RUN_TEST(test_array_struct_is_empty_one_el);
RUN_TEST(test_array_struct_is_empty_zero_el);
RUN_TEST(test_array_struct_size_one_el);
RUN_TEST(test_array_struct_size_zero_el);

UNITY_END();
}
