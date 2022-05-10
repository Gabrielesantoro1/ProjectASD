#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "unity.h"
#include "skip_list.h"
#include <time.h>

static int i1,i2,i3;
static SkipList *skiplist;

static int precedes_int(void *i, void *j){
    if((int)i < (int)j){
        return 1; 
    }else if((int)i == (int)j){
        return 0;
    }else{
        return 2;
    }
}

void setUp(void){
    srand(time(0));
    i1 = 1;
    i2 = 4;
    i3 = 16;
    skiplist = emptySkipList();
    skiplist->compare = precedes_int;
}

void tearDown(void){
    freeSkipList(skiplist);
}

static void test_skiplist_is_empty(void){
    TEST_ASSERT_TRUE(isemptySkipList(skiplist));
}

static void test_skiplist_is_empty_one_el(void){
    insertSkipList(skiplist,(void*)i1);
    TEST_ASSERT_FALSE(isemptySkipList(skiplist));
}

static void test_skiplist_size_zero(void){
    TEST_ASSERT_EQUAL_INT(0,sizeSkipList(skiplist));
}

static void test_skiplist_size_one(void){
    insertSkipList(skiplist,(void*)i1);
    TEST_ASSERT_EQUAL_INT(1,sizeSkipList(skiplist));
}

static void test_skiplist_size_three(void){
    insertSkipList(skiplist,(void*)i1);
    insertSkipList(skiplist,(void*)i2);
    insertSkipList(skiplist,(void*)i3);
    TEST_ASSERT_EQUAL_INT(3,sizeSkipList(skiplist));
}

int main()
{
    UNITY_BEGIN();
    
    RUN_TEST(test_skiplist_is_empty);
    RUN_TEST(test_skiplist_is_empty_one_el);
    RUN_TEST(test_skiplist_size_zero);
    RUN_TEST(test_skiplist_size_one);
    RUN_TEST(test_skiplist_size_three);

    UNITY_END();
}