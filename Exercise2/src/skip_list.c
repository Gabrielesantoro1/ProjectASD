#include "skip_list.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_HEIGHT 20

unsigned int randomLevel(){
    unsigned int lvl = 1;
    while(((float)rand()/(float)RAND_MAX) < 0.5 && lvl < MAX_HEIGHT){
        lvl = lvl + 1;
    }
    return lvl;
}

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

void* insertSkipList(SkipList *skiplist, void* I){
    Node *new = createNode(I, randomLevel());
    if (new->size > skiplist->max_level){
        skiplist->max_level = new->size;
    }
    Node *x = skiplist->head;
    for (int k = skiplist->max_level; k > 0; k--){
        if ((x->next[k] == NULL) || (skiplist->compare(I,x->next[k]->item)==1)){
            if(k <= new->size){
              new->next[k] = x->next[k];
              x->next[k] = new;
            }
        }else{
            x = x->next[k];
            k++;
        }
    }
}

void* searchSkipList(SkipList *skiplist, void* I){
    Node *x = skiplist->head;
    // loop invariant: x->item < I
    for(unsigned int i = skiplist->max_level;i > 0;i--){
        while ((skiplist->compare(I, x->next[i]->item))==1){
            x = x->next[i];
        }
    }
    // x->item < I <= x->next[1]->item
    x = x->next[1];
    if (skiplist->compare(I, x->item)==0){
        return x->item;
    }else{
        return NULL;
    }
}

SkipList* emptySkipList(){
    SkipList *new = malloc(sizeof(SkipList));
    new->max_level = MAX_HEIGHT;
    
    Node *hn = malloc(sizeof(Node));
    hn->next = malloc(sizeof(Node)*new->max_level);
    hn->item = NULL;
    hn->size = 0;
    for(int k = new->max_level; k > 0; k--){
        hn->next[k] = NULL;
    }
    new->head = hn;
    return new;
}

int isemptySkipList(SkipList *skiplist){
    return skiplist->head->next[1] == NULL;
}

int sizeSkipList(SkipList *skiplist){
    unsigned int size = 0;
    Node *tmp = skiplist->head;
    if(tmp->next[1] == NULL){
        return size;
    }else{
        tmp = tmp->next[1];
        while(tmp != NULL){
            size = size + 1;
            tmp = tmp->next[1];
        }
        return size;
    }
}

void freeSkipList(SkipList *skiplist){}

void printSkipList(SkipList *skiplist){
    Node *tmp = skiplist->head;
    tmp = tmp->next[1];
    int count = 1;
    for(;tmp != NULL; tmp = tmp->next[1]){
        printf("\nElem[%i]:%s",count,tmp->item);
        count++;
    }
}
