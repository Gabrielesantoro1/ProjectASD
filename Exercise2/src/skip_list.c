#include "skip_list.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_HEIGHT 50

/**
 * @brief extracts a random value between 1 and MAX_HEIGHT
 * 
 * @return unsigned int 
 */
unsigned int randomLevel(){
    unsigned int lvl = 1;
    while(((float)rand()/(float)RAND_MAX) < 0.5 && lvl < MAX_HEIGHT){
        lvl = lvl + 1;
    }
    return lvl;
}

/**
 * @brief Create a Node object for the Skip List
 * 
 * @param I 
 * @param level 
 * @return Node* 
 */
Node* createNode(void* I, int level){
    Node *new = malloc(sizeof(Node));
    Node **arrayNode = malloc(sizeof(Node)*level);
    for(unsigned int i = 1; i <= level;i++){
        arrayNode[i] = NULL;
    }
    new->next = arrayNode;
    new->size = level;
    new->item = I;

    return new;
}

/**
 * @brief inserts an element in the correct position
 * 
 * @param skiplist 
 * @param I 
 * @return void* 
 */
void* insertSkipList(SkipList *skiplist, void* I){
    Node *new = createNode(I, randomLevel());
    if (new->size > skiplist->max_level){
        skiplist->max_level = new->size;
    }
    Node *x = skiplist->head;
    for (int k = (skiplist->max_level)-1; k > -1; k--){
        if ((x->next[k] == NULL) || (skiplist->compare(I,x->next[k]->item) == 1)){
            if(k < new->size){
              new->next[k] = x->next[k];
              x->next[k] = new;
            }
        }else{
            x = x->next[k];
            k++;
        }
    }
}

/**
 * @brief inserts an element in the correct position - Modified Version
 * 
 * @param skiplist 
 * @param I 
 * @return void* 
 */
void* searchSkipList_modified(SkipList *skiplist, void* I){
    Node *x = skiplist->head;
    // loop invariant: x->item < I
    for(int i = (skiplist->max_level)-1; i > -1; i--){
        if(x->next[i] == NULL){
             //Puntatore di livello i è uguale a NULL
        }else{ 
            while((x->next[i] != NULL) && (skiplist->compare(x->next[i]->item, I)) == 1){
                x = x->next[i];   
            }
            if((x->next[i] != NULL)){
                if((skiplist->compare(x->next[i]->item, I)) == 0){
                    return x->next[i]->item;
                }else{
                    return NULL;
                }
            }else{
                return NULL;
            }
        }
    }
    if(x->next[0] != NULL){
    // x->item < I <= x->next[1]->item
        x = x->next[0];
        if ((skiplist->compare(I, x->item)) == 0){
            return x->item;
        }else{
            return NULL;
        }
    }else{
        return NULL;
    }
}

/**
 * @brief search for an item in the Skip List
 * 
 * @param skiplist 
 * @param I 
 * @return void* 
 */
void* searchSkipList(SkipList *skiplist, void* I){
    Node *x = skiplist->head;
    // loop invariant: x->item < I
    for(int i = (skiplist->max_level)-1; i > -1; i--){
        if(x->next[i] == NULL){
             //Puntatore di livello i è uguale a NULL
        }else{ 
            while((x->next[i] != NULL) && (skiplist->compare(x->next[i]->item, I)) == 1){
                x = x->next[i];   
            }
        }
    }
    if(x->next[0] != NULL){
    // x->item < I <= x->next[1]->item
        x = x->next[0];
        if ((skiplist->compare(I, x->item)) == 0){
            return x->item;
        }else{
            return NULL;
        }
    }else{
        return NULL;
    }
}

/**
 * @brief create an empty Skip List
 * 
 * @return SkipList* 
 */
SkipList* emptySkipList(){
    SkipList *new = malloc(sizeof(SkipList));
    new->max_level = MAX_HEIGHT;
    
    Node *hn = malloc(sizeof(Node));
    hn->next = malloc(sizeof(Node)*new->max_level);
    hn->item = NULL;
    hn->size = 0;
    for(int k = 0; k < new->max_level; k++){
        hn->next[k] = NULL;
    }
    new->head = hn;
    return new;
}

/**
 * @brief 
 * 
 * @param skiplist 
 * @return int 
 */
int isemptySkipList(SkipList *skiplist){
    return skiplist->head->next[0] == NULL;
}

/**
 * @brief return the Skip List size
 * 
 * @param skiplist 
 * @return int 
 */
int sizeSkipList(SkipList *skiplist){
    unsigned int size = 0;
    Node *tmp = skiplist->head;
    if(tmp->next[0] == NULL){
        return size;
    }else{
        size = 1;
        tmp = tmp->next[0];
        while(tmp->next[0] != NULL){
            size = size + 1;
            tmp = tmp->next[0];
        }
        return size;
    }
}

/**
 * @brief frees the memory of the Skip List
 * 
 * @param skiplist 
 */
void freeSkipList(SkipList *skiplist){
    if(isemptySkipList(skiplist)){
        return;
    }else{
        Node *head = skiplist->head;
        for(int i = (skiplist->max_level)-1; i > -1; i--){
            if(head->next[i] != NULL){
                Node *tmp = head->next[i];
                while(tmp->next[i] != NULL){
                    free(head);
                    tmp = tmp->next[i];
                    head = tmp;
                }
            }
        }
    }
}

/**
 * @brief prints the Skip List
 * 
 * @param skiplist 
 */
void printSkipList(SkipList *skiplist){
    Node *tmp = skiplist->head;
    tmp = tmp->next[0];
    int count = 1;
    for(;tmp != NULL; tmp = tmp->next[0]){
        printf("\n");
        printf("\nElem[%i]:%s",count,tmp->item);
        count++;
    }
}
