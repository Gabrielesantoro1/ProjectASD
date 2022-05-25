#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <dirent.h>
#include <limits.h>
#include "skip_list.h"
#include "list.h"
#include <ctype.h>
#include <string.h>

static int precedes_string(void* r1_p,void* r2_p){
  if(r1_p == NULL){
    fprintf(stderr,"precedes_string: the first parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  if(r2_p == NULL){
    fprintf(stderr,"precedes_string: the second parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  if(strcmp((void*)r1_p,(void*)r2_p) == 0){
      return(0);
  }else if(strcmp((void*)r1_p,(void*)r2_p) < 0){
      return(1);
  }
  return(2);
}

static SkipList* load_dictionary(const char* file_name, int (*compare)(void*,void*)){
    char *read_line;
    char buffer[1024];
    int buf_size = 1024;
    FILE *fp;
    fp = fopen(file_name, "r");

    if(fp == NULL){
        fprintf(stderr, "load_dictionary: unable to open the file");
        exit(EXIT_FAILURE);
    }
    
    SkipList *skiplist = emptySkipList();
    skiplist->compare = compare;
    while(fgets(buffer,buf_size,fp) != NULL){
        read_line = malloc((strlen(buffer)+1)*sizeof(char));
        
        if(read_line == NULL){
            fprintf(stderr, "\nload_dictionary: unable to allocate memory for the read line");
            exit(EXIT_FAILURE);
        }

        strcpy(read_line,buffer);
        char *string_field_in_read_line = strtok(read_line, "\n");
        char *string_field = malloc((strlen(string_field_in_read_line)+1)*sizeof(char));
        if(string_field == NULL){
            fprintf(stderr, "load_dictionary: unable to allocate memory for the string field");
            exit(EXIT_FAILURE);
        }

        strcpy(string_field,string_field_in_read_line);
        insertSkipList(skiplist,string_field);
        free(read_line);
    }
    fclose(fp);
    //printf("\nDictionary loaded.\n");
    return skiplist;
}

void writeinfile(double sec, char* filename){
    int i;
    char output[50];
    snprintf(output, 50, "%f", sec);
        FILE * fptr;        
        fptr = fopen(filename, "a");
        for (i = 0; i < strlen(output); i++) {
            fputc(output[i], fptr);
        }
        fputc('\n',fptr);
        fclose(fptr);
}

static char* cleaning_word(char *word){
    char *dst = word;
    for(int i = 0; i < strlen(word); i++){
        if(!ispunct((unsigned int)word[i])){
            if(!isalpha((unsigned int)word[i])){
                dst[i] = word[i];    
            }else{
                dst[i] = tolower(word[i]);
            }
        }else{
            dst[i] = '\0';
        }
    }
    return dst;
}

static List* load_correctme(const char* file_name){
    char *read_word;
    char buffer[1024];
    int buf_size = 1024;
    FILE *fp;
    fp = fopen(file_name, "r");
    
    if(fp == NULL){
        fprintf(stderr, "load_correctme: unnable to load the file");
        exit(EXIT_FAILURE);
    }

    List* list = empytList();    

    while(fgets(buffer,buf_size,fp) != NULL){
        read_word = malloc((strlen(buffer)+1)*sizeof(char)); 
        if(read_word == NULL){
            fprintf(stderr, "read_word: unable to load thw current word");
            exit(EXIT_FAILURE);
        }
        strcpy(read_word, buffer);

        char *read_word_field = strtok(read_word, " "); 
        while(read_word_field != NULL){
            read_word_field = cleaning_word(read_word_field);
            list = list_insert(list, read_word_field);
            read_word_field = strtok(NULL, " ");      
        }
    }
    fclose(fp);
    //printf("\nCorrectme loaded.\n");
    return list_reverse(list);
}

void check_correctme(SkipList *skiplist, List *list){
    for(list; list != NULL; list = list->next){
        if((searchSkipList(skiplist,list->item)) == NULL){
            printf("\n%s",list->item);
        }
    }
}

void check_correctme_modified(SkipList *skiplist, List *list){
    for(list; list != NULL; list = list->next){
        if((searchSkipList_modified(skiplist,list->item)) == NULL){
            printf("\n%s",list->item);
        }
    }
}

static void test_with_comparison_function(const char* dictionary_file_name, const char* correctme_file_name, int (*compare)(void*,void*)){
    srand(time(0));
    //printf("\nLoading data...\n");
    clock_t befor_loading = clock();
    SkipList *dictionary = load_dictionary(dictionary_file_name,compare);
    if(dictionary == NULL){
        fprintf(stderr, "The dictionary file is NULL\n");
    }
    clock_t after_loading = clock();
    double sec_loading = (double)(after_loading-befor_loading) / CLOCKS_PER_SEC;
    writeinfile(sec_loading, "loading.txt");
    //printSkipList(dictionary);

    List *correctme = load_correctme(correctme_file_name);
    if(correctme == NULL){
        fprintf(stderr, "The correctme file is NULL\n");
    }

    //list_print(correctme);
    
    //printf("\nChecking the correctme file...\n");

    clock_t before = clock();
    check_correctme(dictionary,correctme);
    clock_t after = clock();
    double sec = (double)(after-before) / CLOCKS_PER_SEC;

    printf("\n");
    writeinfile(sec, "correction.txt");
    printf("Time take to check the correctme file was: %f;\nThe value of max height was: %d",sec, dictionary->max_level);
    printf("\n");
    
    //Free of the memory section
    list_free(correctme);
    freeSkipList(dictionary);
    printf("Memories free\n");
}

void main(int argc, char *argv[]){
    char* dictionary = argv[1];
    char* correctme = argv[2];
    for(int i = 0; i<25; i++)
        test_with_comparison_function(dictionary, correctme, precedes_string);
}