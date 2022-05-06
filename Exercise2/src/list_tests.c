#include <stdio.h>
#include <stdlib.h>
#include "unity.h"
#include "list.h"

static int i1,i2,i3;
static List *list, *revlist;

void setUp(void){
    i1 = 1;
    i2 = -4;
    i3 = 16;
    list = empytList();
    revlist = empytList();
}

void tearDown(void){
    list_free(list);
}

static void test_list_insert_zero_el(void){
    TEST_ASSERT_TRUE(list_is_empty(list));
}

static void test_list_insert_one_el(void){
    list = list_insert(list, (void*)i1);
    TEST_ASSERT_FALSE(list_is_empty(list));
}

static void test_list_size_zero(void){
    TEST_ASSERT_EQUAL_INT(0,list_size(list));
}

static void test_list_size_three(void){
    list = list_insert(list, (void*)i1);
    list = list_insert(list, (void*)i2);
    list = list_insert(list, (void*)i3);
    TEST_ASSERT_EQUAL_INT(3,list_size(list));
}

static void test_list_reverse_one_el(void){
    list = list_insert(list, (void*)i1);
    revlist = list_insert(revlist, (void*)i1);
    TEST_ASSERT_EQUAL_INT(list->item,revlist->item);
}

static void test_list_reverse_two_el(void){
    list = list_insert(list, (void*)i1);
    list = list_insert(list, (void*)i2);
    revlist = list_insert(revlist, (void*)i2);
    revlist = list_insert(revlist, (void*)i1);
    list = list_reverse(list);
    TEST_ASSERT_EQUAL_INT(list->item,revlist->item);
}

int main()
{
    UNITY_BEGIN();

    RUN_TEST(test_list_insert_zero_el);
    RUN_TEST(test_list_size_zero);
    RUN_TEST(test_list_insert_one_el);
    RUN_TEST(test_list_size_three);
    RUN_TEST(test_list_reverse_one_el);
    RUN_TEST(test_list_reverse_two_el);
    
    UNITY_END();
}