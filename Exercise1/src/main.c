#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct record{
    char* string_field;
    int integer_field;
    float float_field;
}

static int precedes_record_int_field(void* r1_p, void* r2_p){
    if(r1_p == NULL){
        fprintf(stderr,"precedes_record_int_field: the first parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
    if(r2_p == NULL){
        fprintf(stderr,"precedes_record_int_field: the second parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
    struct record *rec1_p = (struct record*)r1_p;
    struct record *rec2_p = (struct record*)r2_p;
    if(rec1_p->integer_field == rec2_p->integer_field){

    }else if(rec1_p->integer_field < rec2_p->integer_field){
        return(1);
    }
    return(0);
}
