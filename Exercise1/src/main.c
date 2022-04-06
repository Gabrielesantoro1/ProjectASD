#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "array.h"

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
        if(strcmp(rec1_p->string_field,rec2_p->string_field)<0){
            return(1);
        }
    }else if(rec1_p->integer_field < rec2_p->integer_field){
        return(1);
    }
    return(0);
}

static int precedes_record_string_field(void* r1_p,void* r2_p){
  if(r1_p == NULL){
    fprintf(stderr,"precedes_record_string_field: the first parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  if(r2_p == NULL){
    fprintf(stderr,"precedes_record_string_field: the second parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
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
    if(r1_p == NULL){
        fprintf(stderr,"precedes_record_float_field: the first parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
    if(r2_p == NULL){
        fprintf(stderr,"precedes_record_float_field: the second parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
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

static void free_array(Array_Struct* array){
    unsigned long el_num = array_size(array);
    for(unsigned long i = 0;i<el_num;i++){
        struct record *array_element = (struct record *)array_get(array, i);
        free(array_element->string_field);
        free(array_element);
    }
    array_free_memory(array);
}

static void print_array(Array_Struct* array){
    unsigned long el_num = array_size(array);
    struct record *array_element;
    printf("\nARRAY OF RECORDS\n");
    for(unsigned long i=0;i<el_num;i++){
        array_element = (struct record*)array_get(array,i);
        printf("< %s , %d , %f >\n", array_element->string_field,array_element->integer_field,array_element->float_field);
    }
}

static void load_array(const char* file_name, OrderedArray* array){
  char *read_line_p;
  char buffer[1024];
  int buf_size = 1024;
  FILE *fp;
  printf("\nLoading data from file...\n");
  fp = fopen(file_name,"r");
  
  //case: if the fopen does not return the pointer to the file
  if(fp == NULL){
    fprintf(stderr,"main: unable to open the file");
    exit(EXIT_FAILURE);
  }
  
  while(fgets(buffer,buf_size,fp) != NULL){  
    read_line_p = malloc((strlen(buffer)+1)*sizeof(char));

    //case: if the malloc goes wrong 
    if(read_line_p == NULL){
      fprintf(stderr,"main: unable to allocate memory for the read line");
      exit(EXIT_FAILURE);
    }
    
    strcpy(read_line_p,buffer);   
    
    char *string_field_in_read_line_p = strtok(read_line_p,",");
    char *integer_field_in_read_line_p = strtok(read_line_p,",");
    char *float_field_in_read_line_p = strtok(NULL,"\n");

    char *string_field = malloc((strlen(string_field_in_read_line_p)+1)*sizeof(char));

    //case: if the malloc goes wrong
    if(string_field == NULL){
      fprintf(stderr,"main: unable to allocate memory for the string field of the read record");
      exit(EXIT_FAILURE);
    }  

    strcpy(string_field,string_field_in_read_line_p);
    int integer_field = atoi(integer_field_in_read_line_p);
   
    //cast from double returned by atof to float.
    float float_field = (float)atof(float_field_in_read_line_p);

    struct record *record_p = malloc(sizeof(struct record));
    
    //case: if the malloc goes wrong
    if(record_p == NULL){
      fprintf(stderr,"main: unable to allocate memory for the read record");
      exit(EXIT_FAILURE);
    }

    record_p->string_field = string_field;
    record_p->integer_field = integer_field;
    record_p->float_field = float_field;
    
    ordered_array_add(array, (void*)record_p);
    free(read_line_p);
  }
  fclose(fp);
  printf("\nData loaded\n");
}

static void test_quicksort_with_comparasion_function(const char* file_name, int (*compare)(void*,void*)){
    Array_Struct* array = array_create();
    clock_t before = clock();
    load_array(file_name, array);

    //quicksort();
    clock_t difference = clock() - before;
    int msec = difference * 1000 / CLOCK_PER_SEC;
    printf("Time taken: %d millisecods",msec%1000);

    print_array(array);
    free_array(array);
}

static void test_insertionsort_with_comparasion_function(const char* file_name, int (*compare)(void*,void*)){
    Array_Struct* array = array_create();
    clock_t before = clock();
    load_array(file_name, array);

    //b_insertionsort();
    clock_t difference = clock() - before;
    int msec = difference * 1000 / CLOCK_PER_SEC;
    printf("Time taken: %d millisecods",msec%1000);

    print_array(array);
    free_array(array);
}

int main(int argc, char const *argv[]){
    if(argc < 2) {
    printf("Usage: ordered_array_main <file_name>\n");
    exit(EXIT_FAILURE);
  }
  test_quicksort_with_comparison_function(argv[1], precedes_record_int_field);
  test_insertionsort_comparison_function(argv[1], precedes_record_string_field);   
  
  return (EXIT_SUCCESS);
}
}