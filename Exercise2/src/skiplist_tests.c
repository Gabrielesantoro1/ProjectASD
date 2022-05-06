#include <stdio.h>
#include <stdlib.h>
#include "unity.h"
#include "list.h"
#include "skip_list.h"

static int i1,i2,i3;
static List *list;

void setUp(void){
    i1 = 1;
    i2 = -4;
    i3 = 16;
    list = empytList();
}

void tearDown(void){
    list_free(list);
}


int main()
{
    UNITY_BEGIN();


    UNITY_END();
}