#include <stdio.h>
#include <stdlib.h>
#include "unity.h"
#include "list.h"
#include "skip_list.h"
#include <time.h>

static int i1,i2,i3;
static SkipList* skiplist;

void setUp(void){
    srand((unsigned int)time);
    i1 = 1;
    i2 = -4;
    i3 = 16;
    skiplist = emptySkipList();
}

void tearDown(void){

}

static void test_skiplist_is_empty(void){
    TEST_ASSERT_TRUE(isemptySkipList(skiplist));
}

static void test_skiplist_is_empty_one_el(void){
    insertSkipList(skiplist,(void*)i1);
    TEST_ASSERT_FALSE(isemptySkipList(skiplist));
}

static void test_skiplist_is_empty_two_el(void){
    insertSkipList(skiplist,(void*)i1);
    insertSkipList(skiplist,(void*)i2);
    TEST_ASSERT_FALSE(isemptySkipList(skiplist));
}

static void test_skiplist_size_zero(void){
    TEST_ASSERT_EQUAL(0,sizeSkipList(skiplist));
}

static void test_skiplist_size_one(void){
    insertSkipList(skiplist,(void*)i1);
    TEST_ASSERT_EQUAL(1,sizeSkipList(skiplist));
}

int main()
{
    UNITY_BEGIN();
    RUN_TEST(test_skiplist_is_empty);
    RUN_TEST(test_skiplist_is_empty_one_el);
    RUN_TEST(test_skiplist_is_empty_two_el);
    RUN_TEST(test_skiplist_size_zero);
    RUN_TEST(test_skiplist_size_one);

    UNITY_END();
}