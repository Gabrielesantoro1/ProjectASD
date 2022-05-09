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
    return skiplist;
}

//Aggiungere anche cambimaneto caps lock word
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
    return list_reverse(list);
}

void check_correctme(SkipList *skiplist, List *list){
    for(List *tmp = list; tmp != NULL; tmp = tmp->next){
        //printf("\n");
        //printf("\nWord to search:%s",tmp->item);
        if((searchSkipList(skiplist,tmp->item)) == NULL){
            printf("\n%s",tmp->item);
        }
    }
}

static void test_with_comparison_function(const char* dictionary_file_name, const char* correctme_file_name, int (*compare)(void*,void*)){
    SkipList *dictionary = load_dictionary(dictionary_file_name,compare);
    if(dictionary == NULL){
        fprintf(stderr, "The dictionary file is NULL\n");
    }
    printf("\nDictionary loaded.\n");
    //printSkipList(dictionary);

    List *correctme = load_correctme(correctme_file_name);
    if(correctme == NULL){
        fprintf(stderr, "The correctme file is NULL\n");
    }
    printf("\nCorrectme loaded.\n");
    //list_print(correctme);
    
    check_correctme(dictionary,correctme);

    list_free(correctme);
    //freeSkipList(dictionary);

    printf("\nCorrectme have been free.");
}

void main(int argc){
    /*
    char* dictionary;
    printf("Insert the dictionary path to order: ");
    scanf("%s", dictionary);
    
    char* correctme;
    printf("Insert the correctme path to order: ");
    scanf("%s", correctme);
    */
    srand(time(0));
    test_with_comparison_function("dictionary.txt", "correctme.txt", precedes_string);
}